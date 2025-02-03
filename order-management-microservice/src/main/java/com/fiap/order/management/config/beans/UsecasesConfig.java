package com.fiap.order.management.config.beans;

import com.fiap.order.management.service.OrderService;
import com.fiap.order.management.usecase.CreateOrderUseCase;
import com.fiap.order.management.usecase.FindOrderByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsecasesConfig {

  @Bean
  public CreateOrderUseCase createOrderUseCase(OrderService orderService) {
    return new CreateOrderUseCase(orderService);
  }

  @Bean
  public FindOrderByIdUseCase findOrderByIdUseCase(OrderService orderService) {
    return new FindOrderByIdUseCase(orderService);
  }
}
