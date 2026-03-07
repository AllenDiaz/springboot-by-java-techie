package com.demo.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot WebFlux demo application.
 *
 * Spring WebFlux is a reactive, non-blocking web framework built on
 * Project Reactor. It handles concurrency with a small number of threads
 * and scales with fewer hardware resources than traditional Servlet-based stacks.
 */
@SpringBootApplication
public class WebFluxDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxDemoApplication.class, args);
    }
}
