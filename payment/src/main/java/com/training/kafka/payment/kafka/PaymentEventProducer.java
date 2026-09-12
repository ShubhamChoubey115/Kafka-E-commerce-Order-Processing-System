package com.training.kafka.payment.kafka;

import com.training.kafka.payment.event.PaymentCompletedEvent;
import com.training.kafka.payment.event.PaymentFailedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventProducer(
            KafkaTemplate<String, Object> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishCompleted(PaymentCompletedEvent event) {

        kafkaTemplate.send(
                "payment.completed",
                event.orderId().toString(),
                event
        );
    }

    public void publishFailed(PaymentFailedEvent event) {

        kafkaTemplate.send(
                "payment.failed",
                event.orderId().toString(),
                event
        );
    }
}
