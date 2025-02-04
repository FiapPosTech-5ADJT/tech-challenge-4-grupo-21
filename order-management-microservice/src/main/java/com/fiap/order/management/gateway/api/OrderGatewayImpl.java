package com.fiap.order.management.gateway.api;

import com.fiap.order.management.gateway.OrderGateway;
import com.fiap.order.management.entity.Order;
import com.fiap.order.management.repository.OrderRepository;

import java.util.Optional;

public class OrderGatewayImpl implements OrderGateway {

  private final OrderRepository orderRepository;


  public OrderGatewayImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Order save(Order order) {
    return orderRepository.save(order);
  }

  @Override
  public Optional<Order> findById(Long orderId) {
    return orderRepository.findById(orderId);
  }

  @Override
  public boolean existsById(Long orderId) {
    return orderRepository.existsById(orderId);
  }
}
