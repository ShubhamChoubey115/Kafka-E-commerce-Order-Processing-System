package com.training.kafka.payment.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentCompletedEvent(
        UUID eventId,
        UUID orderId,
        LocalDateTime completedAt
) {
}
