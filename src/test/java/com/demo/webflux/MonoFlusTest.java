package com.demo.webflux;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFlusTest {

    @Test
    public void testMono() {
        Mono<?> monoString = Mono.just("javatechie")
                .then(Mono.error(new RuntimeException("Exception Occurred")))
                .log();
                
        monoString.subscribe(System.out::println, (e)-> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Hibernate", "Microservices")
                .concatWithValues("Aws")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred in Flux")))
                .log();
            

        fluxString.subscribe(System.out::println, (e)-> System.out.println(e.getMessage()));  
    }       
}
