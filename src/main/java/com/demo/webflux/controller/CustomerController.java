package com.demo.webflux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.webflux.dto.Customer;

import com.demo.webflux.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;
    
    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.loadAllCustomers();
    }

    @GetMapping("/stream")
    public Flux<Customer> getAllCustomersStream() {
        return service.loadAllCustomersStream();
    } 
}
