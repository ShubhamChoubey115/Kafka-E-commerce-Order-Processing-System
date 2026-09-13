package com.training.kafka.notification.kafka;

import com.training.kafka.notification.event.*;
import com.training.kafka.notification.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventConsumer {

    private final NotificationService notificationService;

    public NotificationEventConsumer(
            NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    @KafkaListener(
            topics = "order.created",
            groupId = "notification-service"
    )
    public void consumeOrderCreated(OrderCreatedEvent event) {

        notificationService.notifyOrderCreated(event);
    }

    @KafkaListener(
            topics = "inventory.reserved",
            groupId = "notification-service"
    )
    public void consumeInventoryReserved(
            InventoryReservedEvent event) {

        notificationService.notifyInventoryReserved(event);
    }

    @KafkaListener(
            topics = "inventory.rejected",
            groupId = "notification-service"
    )
    public void consumeInventoryRejected(
            InventoryRejectedEvent event) {

        notificationService.notifyInventoryRejected(event);
    }

    @KafkaListener(
            topics = "payment.completed",
            groupId = "notification-service"
    )
    public void consumePaymentCompleted(
            PaymentCompletedEvent event) {

        notificationService.notifyPaymentCompleted(event);
    }

    @KafkaListener(
            topics = "payment.failed",
            groupId = "notification-service"
    )
    public void consumePaymentFailed(
            PaymentFailedEvent event) {

        notificationService.notifyPaymentFailed(event);
    }
}

