package com.training.kafka.inventory.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record InventoryRejectedEvent(
        UUID eventId,
        UUID orderId,
        String reason,
        LocalDateTime rejectedAt
) {
}
