package com.demo.webflux.controller;

import com.demo.webflux.model.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

/**
 * Reactive REST controller demonstrating Mono and Flux.
 *
 * Mono  — a Publisher that emits 0 or 1 item, then completes or errors.
 * Flux  — a Publisher that emits 0..N items, then completes or errors.
 *
 * Both are cold publishers: they only start producing when subscribed to.
 * The WebFlux framework subscribes on every incoming HTTP request.
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    // -----------------------------------------------------------------------
    // Mono examples
    // -----------------------------------------------------------------------

    /**
     * GET /api/hello
     * Returns a single greeting wrapped in a Mono.
     */
    @GetMapping("/hello")
    public Mono<Message> hello() {
        return Mono.just(new Message("Hello from Spring WebFlux!", Instant.now().toString()));
    }

    /**
     * GET /api/hello/{name}
     * Demonstrates Mono.fromCallable() for deferred computation.
     */
    @GetMapping("/hello/{name}")
    public Mono<Message> helloName(@PathVariable String name) {
        return Mono.fromCallable(() ->
                new Message("Hello, %s! Welcome to reactive programming.".formatted(name),
                        Instant.now().toString())
        );
    }

    // -----------------------------------------------------------------------
    // Flux examples
    // -----------------------------------------------------------------------

    /**
     * GET /api/stream
     * Returns a Flux that emits 5 messages with a 1-second delay between each.
     * Uses SERVER-SENT EVENTS so the client receives items as they arrive.
     *
     * Key concept: Flux.interval() is inherently non-blocking — the thread
     * is not parked between emissions; the event loop schedules each item.
     */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> stream() {
        return Flux.interval(Duration.ofSeconds(1))
                   .take(5)
                   .map(seq -> new Message("Event #" + seq, Instant.now().toString()));
    }

    /**
     * GET /api/messages
     * Returns a Flux of static messages as a JSON array.
     * WebFlux collects all items before writing the response.
     */
    @GetMapping("/messages")
    public Flux<Message> messages() {
        return Flux.just(
                new Message("Reactive Streams: back-pressure aware", Instant.now().toString()),
                new Message("Project Reactor: Mono and Flux", Instant.now().toString()),
                new Message("Spring WebFlux: non-blocking I/O", Instant.now().toString()),
                new Message("Netty: default embedded server", Instant.now().toString())
        );
    }
}
