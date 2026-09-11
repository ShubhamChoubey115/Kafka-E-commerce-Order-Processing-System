package com.training.kafka.inventory.kafka;


import com.training.kafka.inventory.event.OrderCreatedEvent;
import com.training.kafka.inventory.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventConsumer {

    private final InventoryService inventoryService;

    public InventoryEventConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(
            topics = "order.created",
            groupId = "inventory-service"
    )
    public void consumeOrderCreated(OrderCreatedEvent event) {

        System.out.println(
                "Received OrderCreatedEvent: " + event.orderId()
        );

        inventoryService.processOrder(event);
    }
}