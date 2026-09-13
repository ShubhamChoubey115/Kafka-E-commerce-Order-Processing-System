package com.training.kafka.notification.service;

import com.training.kafka.notification.event.*;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notifyOrderCreated(OrderCreatedEvent event) {

        System.out.println(
                "📧 Notification: Order created - "
                        + event.orderId()
        );
    }

    public void notifyInventoryReserved(
            InventoryReservedEvent event) {

        System.out.println(
                "📦 Notification: Inventory reserved for order - "
                        + event.orderId()
        );
    }

    public void notifyInventoryRejected(
            InventoryRejectedEvent event) {

        System.out.println(
                "❌ Notification: Inventory rejected for order - "
                        + event.orderId()
                        + ", reason: "
                        + event.reason()
        );
    }

    public void notifyPaymentCompleted(
            PaymentCompletedEvent event) {

        System.out.println(
                "💳 Notification: Payment completed for order - "
                        + event.orderId()
        );
    }

    public void notifyPaymentFailed(
            PaymentFailedEvent event) {

        System.out.println(
                "❌ Notification: Payment failed for order - "
                        + event.orderId()
                        + ", reason: "
                        + event.reason()
        );
    }
}

