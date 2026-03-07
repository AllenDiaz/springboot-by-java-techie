package com.demo.webflux.controller;

import com.demo.webflux.model.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

/**
 * Slice test for HelloController using WebTestClient (no real HTTP server).
 *
 * @WebFluxTest spins up only the WebFlux layer — fast and lightweight.
 * StepVerifier lets us assert reactive stream emissions step by step.
 */
@WebFluxTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private WebTestClient webClient;

    // -----------------------------------------------------------------------
    // /api/hello
    // -----------------------------------------------------------------------

    @Test
    void hello_returnsMonoWithMessage() {
        webClient.get()
                 .uri("/api/hello")
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody(Message.class)
                 .value(msg -> {
                     Assertions.assertEquals("Hello from Spring WebFlux!", msg.content());
                     Assertions.assertNotNull(msg.timestamp());
                 });
    }

    // -----------------------------------------------------------------------
    // /api/hello/{name}
    // -----------------------------------------------------------------------

    @Test
    void helloName_returnsPersonalisedMessage() {
        webClient.get()
                 .uri("/api/hello/Alice")
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody(Message.class)
                 .value(msg -> Assertions.assertTrue(msg.content().contains("Alice")));
    }

    // -----------------------------------------------------------------------
    // /api/messages
    // -----------------------------------------------------------------------

    @Test
    void messages_returnsFluxAsJsonArray() {
        webClient.get()
                 .uri("/api/messages")
                 .accept(MediaType.APPLICATION_JSON)
                 .exchange()
                 .expectStatus().isOk()
                 .expectBodyList(Message.class)
                 .hasSize(4);
    }

    // -----------------------------------------------------------------------
    // StepVerifier — verifying Mono directly without HTTP layer
    // -----------------------------------------------------------------------

    @Test
    void hello_stepVerifier() {
        HelloController controller = new HelloController();

        StepVerifier.create(controller.hello())
                    .expectNextMatches(msg -> msg.content().equals("Hello from Spring WebFlux!"))
                    .verifyComplete();
    }

    // -----------------------------------------------------------------------
    // StepVerifier — verifying Flux directly
    // -----------------------------------------------------------------------

    @Test
    void messages_stepVerifier() {
        HelloController controller = new HelloController();

        StepVerifier.create(controller.messages())
                    .expectNextCount(4)
                    .verifyComplete();
    }
}
