# Practical Demonstration

## Steps
- **Create a Spring Boot WebFlux project**
  - Initialize with Spring Initializr
  - Add dependency: `spring-boot-starter-webflux`

- **Write test cases**
  - For `Mono` → single-value publisher
  - For `Flux` → multi-value publisher

- **Observe workflow logs**
  - `subscribe()` → subscriber attaches
  - `request(n)` → demand signaled
  - `onNext` events → data emitted