package com.fiap.order.management.config.beans;

import com.fiap.order.management.clients.CustomerClient;
import com.fiap.order.management.gateway.CustomerGateway;
import com.fiap.order.management.gateway.api.CustomerGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

  @Bean
  CustomerGateway customerGateway( CustomerClient customerClient) {
    return new CustomerGatewayImpl( customerClient);
  }
}
