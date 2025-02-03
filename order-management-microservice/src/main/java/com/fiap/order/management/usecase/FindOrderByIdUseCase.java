package com.fiap.order.management.usecase;

import com.fiap.order.management.domain.OrderDomain;
import com.fiap.order.management.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class FindOrderByIdUseCase {
    private final OrderService orderService;

  public FindOrderByIdUseCase(OrderService orderService) {
    this.orderService = orderService;
  }

  public OrderDomain execute(Long orderId) {
        return orderService.findById(orderId);
    }
}
