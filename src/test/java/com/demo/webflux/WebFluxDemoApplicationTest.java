package com.demo.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * Integration smoke-test: verifies the Spring application context
 * loads without errors (all beans wired correctly).
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebFluxDemoApplicationTest {

    @Test
    void contextLoads() {
        // If the context fails to start, this test fails immediately.
        // No assertions needed — the test itself is the assertion.
    }
}
