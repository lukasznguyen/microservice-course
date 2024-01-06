package com.mycompany.notification.rabbitmq;

import com.mycompany.clients.notifications.NotificationRequest;
import com.mycompany.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService service;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consume(NotificationRequest request) {
        log.info("Consumed {} from queue", request);
        service.send(request);
    }

}