package com.mongodb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.model.Customer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

public class Sample {
    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

        List<Customer> custList = new RestTemplate().getForObject("http://localhost:8080/getAllCustomersList", List.class);
        System.out.println("RestTemplate: " + custList);

//        Mono<Customer> cust = WebClient.builder().baseUrl("http://localhost:8080").build().get().uri("/getAllCustomersList").retrieve().bodyToMono(Customer.class);
        WebClient webClient = WebClient.builder().build();
        Flux<Customer> flux = webClient.get()
                .uri("http://localhost:8080/getAllCustomersList")
                .retrieve()
                .bodyToFlux(Customer.class);

        flux
                .map(rawJson -> {
                    try {
                        // Beautify JSON data
                        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rawJson);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return rawJson;  // If beautification fails, return the raw JSON
                    }
                })
                .blockLast();
    }
}
