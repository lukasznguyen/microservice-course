package com.mycompany.notification;

import com.mycompany.clients.notifications.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository repository) {

    public void send(NotificationRequest request) {
        repository.save(
                Notification.builder()
                        .toCustomerId(request.toCustomerId())
                        .toCustomerEmail(request.toCustomerEmail())
                        .sender("Amigoscode")
                        .message(request.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }

}