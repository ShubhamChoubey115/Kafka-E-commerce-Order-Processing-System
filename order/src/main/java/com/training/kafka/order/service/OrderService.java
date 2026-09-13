package com.training.kafka.order.service;


import com.training.kafka.order.dto.CreateOrderRequest;
import com.training.kafka.order.dto.OrderResponse;
import com.training.kafka.order.entity.Order;
import com.training.kafka.order.entity.OrderStatus;
import com.training.kafka.order.event.OrderCreatedEvent;
import com.training.kafka.order.kafka.OrderEventProducer;
import com.training.kafka.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    public OrderService(OrderRepository orderRepository, OrderEventProducer orderEventProducer) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {

        Order order = new Order();

        order.setCustomerId(request.customerId());
        order.setTotalAmount(request.totalAmount());
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent(
                UUID.randomUUID(),
                savedOrder.getId(),
                savedOrder.getCustomerId(),
                savedOrder.getTotalAmount(),
                savedOrder.getCreatedAt()
        );

        orderEventProducer.publishOrderCreated(event);

        return toResponse(savedOrder);
    }

    public OrderResponse getOrder(UUID id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found: " + id)
                );

        return toResponse(order);
    }

    private OrderResponse toResponse(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}