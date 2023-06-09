package com.mycompany.customer.fraud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudController(FraudCheckService service) {

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
        boolean fraudulentCustomer = service.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(fraudulentCustomer);
    }

}