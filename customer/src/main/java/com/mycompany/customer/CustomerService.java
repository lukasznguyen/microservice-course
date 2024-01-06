package com.mycompany.customer;

import com.mycompany.amqp.RabbitMQMessageProducer;
import com.mycompany.clients.fraud.FraudCheckResponse;
import com.mycompany.clients.fraud.FraudClient;
import com.mycompany.clients.notifications.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
        CustomerRepository repository,
        FraudClient fraudClient,
        RabbitMQMessageProducer messageProducer) {
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

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Amigoscode...", customer.getFirstName())
        );
        messageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}