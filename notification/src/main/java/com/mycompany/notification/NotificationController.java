package com.mycompany.notification;

import com.mycompany.clients.notifications.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/notifications")
public record NotificationController(NotificationService service) {

    @PostMapping
    void sendNotification(@RequestBody NotificationRequest request) {
        log.info("New notification... {}", request);
        service.send(request);
    }

}