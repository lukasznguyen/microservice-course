package com.mycompany.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.mycompany.notification", "com.mycompany.amqp"})
@EnableEurekaClient
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig notificationConfig) {
//        return args -> producer.publish(
//                new Person("Harvey", 30),
//                notificationConfig.getInternalExchange(),
//                notificationConfig.getInternalNotificationRoutingKey()
//        );
//    }
//
//    record Person(String name, int age) {
//    }

}