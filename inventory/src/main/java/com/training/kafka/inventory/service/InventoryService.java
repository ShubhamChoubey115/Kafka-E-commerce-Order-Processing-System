package com.training.kafka.inventory.service;

import com.training.kafka.inventory.event.InventoryRejectedEvent;
import com.training.kafka.inventory.event.InventoryReservedEvent;
import com.training.kafka.inventory.event.OrderCreatedEvent;
import com.training.kafka.inventory.kafka.InventoryEventProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class InventoryService {

    private final InventoryEventProducer eventProducer;

    public InventoryService(InventoryEventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    public void processOrder(OrderCreatedEvent event) {

        boolean stockAvailable = false;

        if (stockAvailable) {

            InventoryReservedEvent reservedEvent =
                    new InventoryReservedEvent(
                            UUID.randomUUID(),
                            event.orderId(),
                            LocalDateTime.now()
                    );

            eventProducer.publishReserved(reservedEvent);

            System.out.println(
                    "Inventory reserved for order: "
                            + event.orderId()
            );

        } else {

            InventoryRejectedEvent rejectedEvent =
                    new InventoryRejectedEvent(
                            UUID.randomUUID(),
                            event.orderId(),
                            "Insufficient inventory",
                            LocalDateTime.now()
                    );

            eventProducer.publishRejected(rejectedEvent);
        }
    }
}