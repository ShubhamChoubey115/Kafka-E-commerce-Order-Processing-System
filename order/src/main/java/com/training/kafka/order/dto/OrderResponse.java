package com.training.kafka.order.dto;

import com.training.kafka.order.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(

        UUID id,
        UUID customerId,
        BigDecimal totalAmount,
        OrderStatus status,
        LocalDateTime createdAt

) {
}