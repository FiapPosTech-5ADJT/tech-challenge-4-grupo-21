package com.fiap.order.management.config.beans;

import com.fiap.order.management.gateway.StockGateway;
import com.fiap.order.management.gateway.api.StockGatewayImpl;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfig {
  @Bean
  public StockGateway stockGateway(StreamBridge streamBridge) {
    return new StockGatewayImpl(streamBridge);
  }
}
