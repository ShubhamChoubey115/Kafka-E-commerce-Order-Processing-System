package com.training.kafka.notification.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record InventoryReservedEvent(
        UUID eventId,
        UUID orderId,
        LocalDateTime reservedAt
) {
}