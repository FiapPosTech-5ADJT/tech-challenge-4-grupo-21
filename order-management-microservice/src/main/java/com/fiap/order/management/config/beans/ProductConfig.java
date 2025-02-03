package com.fiap.order.management.config.beans;

import com.fiap.order.management.clients.ProductClient;
import com.fiap.order.management.gateway.ProductGateway;
import com.fiap.order.management.gateway.api.ProductGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
  @Bean
  ProductGateway productGateway(ProductClient productClient) {
    return new ProductGatewayImpl(productClient);
  }
}
