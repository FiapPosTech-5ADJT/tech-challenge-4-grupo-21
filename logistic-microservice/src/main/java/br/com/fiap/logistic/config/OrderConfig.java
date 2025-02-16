package br.com.fiap.logistic.config;

import br.com.fiap.logistic.adapter.OrderConverter;
import br.com.fiap.logistic.gateway.OrderGateway;
import br.com.fiap.logistic.gateway.impl.OrderGatewayImpl;
import br.com.fiap.logistic.repository.OrderRepository;
import br.com.fiap.logistic.service.OrderService;
import br.com.fiap.logistic.service.impl.OrderServiceImpl;
import br.com.fiap.logistic.usecase.GetOrdersByZipCodeUseCase;
import br.com.fiap.logistic.usecase.UpdateOrderStatusUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    OrderConverter orderConverter() {
        return new OrderConverter();
    }

    @Bean
    OrderGateway orderGateway(OrderRepository orderRepository) {
        return new OrderGatewayImpl(orderRepository);
    }

    @Bean
    OrderService orderService(OrderGateway orderGateway, OrderConverter orderConverter) {
        return new OrderServiceImpl(orderGateway, orderConverter);
    }

    @Bean
    UpdateOrderStatusUseCase updateOrderStatusUseCase(OrderService orderService) {
        return new UpdateOrderStatusUseCase(orderService);
    }

    @Bean
    GetOrdersByZipCodeUseCase getOrdersByZipCodeUseCase(OrderService orderService) {
        return new GetOrdersByZipCodeUseCase(orderService);
    }
}
