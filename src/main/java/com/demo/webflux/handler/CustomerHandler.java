package com.demo.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.BodyInserters;
import com.demo.webflux.dao.CustomerDao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.demo.webflux.dto.Customer;

@Service
public class CustomerHandler {
    
    @Autowired
    private CustomerDao dao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customers = dao.getCustomerList();
        return ServerResponse.ok().body(customers, Customer.class); 
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
        int customerId = Integer.valueOf(request.pathVariable("input"));
            // dao.getCustomerList().filter(c -> c.getId() == customerId).take(1).single();
            Mono<Customer> customerMono = dao.getCustomerList().filter(c -> c.getId() == customerId).next();
            return ServerResponse.ok().body(customerMono, Customer.class);
        
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saverResponse = customerMono.map(dto->dto.getId()+":"+dto.getName());
        // saverResponse.subscribe(data -> System.out.println("Customer data : " + data));
        return ServerResponse.ok().body(saverResponse, String.class);
    }
}
