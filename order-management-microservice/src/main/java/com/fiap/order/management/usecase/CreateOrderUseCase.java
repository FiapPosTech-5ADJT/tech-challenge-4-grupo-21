package com.fiap.order.management.usecase;

import com.fiap.order.management.domain.OrderDomain;
import com.fiap.order.management.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderUseCase {
  private final OrderService orderService;

  public CreateOrderUseCase(OrderService orderService) {
    this.orderService = orderService;
  }

  public OrderDomain execute(OrderDomain orderDomain) {
    return orderService.create(orderDomain);
  }
}
