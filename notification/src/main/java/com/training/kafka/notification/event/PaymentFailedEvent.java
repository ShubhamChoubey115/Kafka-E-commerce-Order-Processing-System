package com.training.kafka.notification.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentFailedEvent(
        UUID eventId,
        UUID orderId,
        String reason,
        LocalDateTime failedAt
) {
}