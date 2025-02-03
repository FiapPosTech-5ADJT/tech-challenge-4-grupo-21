package com.fiap.order.management.config.beans;

import com.fiap.order.management.gateway.LogisticGateway;
import com.fiap.order.management.gateway.api.LogisticGatewayImpl;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogisticConfig {

     @Bean
     public LogisticGateway logisticGateway(StreamBridge streamBridge) {
         return new LogisticGatewayImpl(streamBridge);
    }
}
