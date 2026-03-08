package com.demo.webflux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.webflux.dto.Customer;

import reactor.core.publisher.Flux;

import com.demo.webflux.dao.CustomerDao;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total time taken to load customers : " + (end - start) + " ms");
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {

        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total time taken to load customers : " + (end - start) + " ms");
        return customers;
    }
}
