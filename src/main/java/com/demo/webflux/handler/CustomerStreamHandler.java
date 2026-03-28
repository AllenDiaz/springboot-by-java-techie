package com.demo.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.BodyInserters;

import com.demo.webflux.dao.CustomerDao;
import com.demo.webflux.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao dao;

    private CustomerStreamHandler() {
    
    }

    public Mono<ServerResponse> GetCustomer(ServerRequest request) {
        Flux<Customer> customerStream = dao.getCustomersStream();
        
        return ServerResponse.ok().
                contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerStream, Customer.class);
    }
}
