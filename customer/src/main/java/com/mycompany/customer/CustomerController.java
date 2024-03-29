package com.mycompany.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService service) {

    @PostMapping
    public void register(@RequestBody CustomerRegistrationRequest request) {
        log.info("new customer registration {}", request);
        service.register(request);
    }

}