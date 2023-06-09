package com.mycompany.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService() {
    public void register(CustomerRegistrationRequest request) {
        var customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        // todo: store customer in db
    }
}