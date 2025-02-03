package com.fiap.order.management.config.beans;

import com.fiap.order.management.gateway.OrderGateway;
import com.fiap.order.management.gateway.api.OrderGatewayImpl;
import com.fiap.order.management.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
  @Bean
  OrderGateway orderGateway(OrderRepository orderRepository) {
    return new OrderGatewayImpl(orderRepository);
  }
}
