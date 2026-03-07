# ============================================================
# Stage 1 — Build
# Uses the full Maven image to compile and package the JAR.
# Keeping build tooling out of the final image reduces its size
# and attack surface.
# ============================================================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /workspace

# Corporate SSL proxy bypass (Safeway network intercepts TLS, causing PKIX errors).
#
# Root cause: Maven 3.9+ uses a *native* HTTP transport by default.
# The maven.wagon.http.ssl.* flags are silently ignored by the native transport —
# they only apply to the legacy Wagon transport.
#
# Fix: force Wagon transport (-Dmaven.resolver.transport=wagon) so the SSL
# bypass flags below are actually honoured.
# These ENV vars are scoped to this build stage only and do not leak into
# the final runtime image.
ENV MAVEN_OPTS="-Dmaven.resolver.transport=wagon \
                -Dmaven.wagon.http.ssl.insecure=true \
                -Dmaven.wagon.http.ssl.allowall=true \
                -Dmaven.wagon.http.ssl.ignore.validity.dates=true \
                -Dhttps.protocols=TLSv1.2,TLSv1.3"

# Copy dependency manifests first so Docker can cache the
# downloaded dependencies layer independently of source changes.
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build the fat JAR (skipping tests here;
# tests run as a dedicated CI step).
COPY src ./src
RUN mvn package -DskipTests -B

# ============================================================
# Stage 2 — Run
# eclipse-temurin is the official replacement for the deprecated
# openjdk Docker Hub images. Using the same family as the build
# stage lets Docker reuse the cached registry auth and manifest.
# The -jre variant is runtime-only (no compiler), reducing image size.
# ============================================================
FROM eclipse-temurin:17-jre-jammy

# Non-root user for security best practice
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

WORKDIR /app

# Copy only the fat JAR produced in the build stage
COPY --from=build /workspace/target/*.jar app.jar

# Expose the port the application listens on
EXPOSE 8080

# JVM tuning flags suitable for containers:
#   -XX:+UseContainerSupport   — respect cgroup CPU/memory limits
#   -XX:MaxRAMPercentage=75.0  — use up to 75 % of container RAM for heap
ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-jar", "app.jar"]
