package com.training.kafka.payment.kafka;


import com.training.kafka.payment.event.OrderCreatedEvent;
import com.training.kafka.payment.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventConsumer {

    private final PaymentService paymentService;

    public PaymentEventConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(
            topics = "order.created",
            groupId = "payment-service"
    )
    public void consumeOrderCreated(OrderCreatedEvent event) {

        System.out.println(
                "Payment received order: " + event.orderId()
        );

        paymentService.processPayment(event);
    }
}
