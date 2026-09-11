package com.training.kafka.inventory.kafka;

import com.training.kafka.inventory.event.InventoryRejectedEvent;
import com.training.kafka.inventory.event.InventoryReservedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InventoryEventProducer(
            KafkaTemplate<String, Object> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishReserved(InventoryReservedEvent event) {

        kafkaTemplate.send(
                "inventory.reserved",
                event.orderId().toString(),
                event
        );
    }

    public void publishRejected(InventoryRejectedEvent event) {

        kafkaTemplate.send(
                "inventory.rejected",
                event.orderId().toString(),
                event
        );
    }
}
