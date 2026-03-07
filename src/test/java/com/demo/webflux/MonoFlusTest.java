package com.demo.webflux;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoFlusTest {

    @Test
    public void testMono() {
        Mono<String> monoString = Mono.just("javatechie");
        monoString.subscribe(System.out::println);
    }
}
