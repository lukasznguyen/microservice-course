package com.mycompany.customer;

import com.mycompany.clients.fraud.FraudCheckResponse;
import com.mycompany.clients.fraud.FraudClient;
import com.mycompany.clients.notifications.NotificationClient;
import com.mycompany.clients.notifications.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
        CustomerRepository repository,
        NotificationClient notificationClient,
        FraudClient fraudClient) {
    public void register(CustomerRegistrationRequest request) {
        var customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        repository.saveAndFlush(customer);
        // todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // todo: make it async - add to queue
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to Amigoscode...", customer.getFirstName())
                )
        );
    }
}