package com.training.kafka.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOrderRequest(

        @NotNull
        UUID customerId,

        @NotNull
        @Positive
        BigDecimal totalAmount

) {
}