package com.mongodb.controller;

import com.mongodb.model.Customer;
import com.mongodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository custRepo;

    @GetMapping("/getCustomersList")
    public Mono<Customer> getCustomersList() {
        return Mono.from(custRepo.findAll());
    }

    @GetMapping("/getAllCustomersList")
    public Flux<Customer> getAllCustomersList() {
        return custRepo.findAll();
    }

    @PostMapping("/add/customer")
    public Mono<Customer> addCustomer(@RequestBody Customer customer) {
        return custRepo.save(customer);
    }

    @GetMapping("/getCustById")
    public Mono<Customer> getCustomerById(@RequestParam int custId) {
        return custRepo.findById(custId);
    }
}
