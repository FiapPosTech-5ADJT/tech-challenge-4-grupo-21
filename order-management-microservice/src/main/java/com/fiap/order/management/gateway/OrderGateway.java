package com.fiap.order.management.gateway;

import com.fiap.order.management.entity.Order;

import java.util.Optional;

public interface OrderGateway {
  Order save(Order order);
  Optional<Order> findById(Long orderId);
  boolean existsById(Long orderId);
}

