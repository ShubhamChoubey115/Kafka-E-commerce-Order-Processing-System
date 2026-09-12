package com.training.kafka.payment.service;

import com.training.kafka.payment.event.*;
import com.training.kafka.payment.kafka.PaymentEventProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentEventProducer eventProducer;

    public PaymentService(PaymentEventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    public void processPayment(OrderCreatedEvent event) {

        boolean paymentSuccessful = true;

        if (paymentSuccessful) {

            PaymentCompletedEvent completedEvent =
                    new PaymentCompletedEvent(
                            UUID.randomUUID(),
                            event.orderId(),
                            LocalDateTime.now()
                    );

            eventProducer.publishCompleted(completedEvent);

            System.out.println(
                    "Payment completed for order: "
                            + event.orderId()
            );

        } else {

            PaymentFailedEvent failedEvent =
                    new PaymentFailedEvent(
                            UUID.randomUUID(),
                            event.orderId(),
                            "Payment declined",
                            LocalDateTime.now()
                    );

            eventProducer.publishFailed(failedEvent);
        }
    }
}
