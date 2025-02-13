package com.fiap.order.management.gateway;

import com.fiap.order.management.entity.Order;
import com.fiap.order.management.gateway.api.OrderGatewayImpl;
import com.fiap.order.management.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class OrderGatewayImplTest {

    private OrderRepository orderRepository;
    private OrderGatewayImpl orderGateway;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderGateway = new OrderGatewayImpl(orderRepository);
    }

    @Test
    void shouldSaveOrder() {
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderGateway.save(order);

        assertThat(savedOrder).isEqualTo(order);
    }

    @Test
    void shouldFindOrderById() {
        Long orderId = 1L;
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderGateway.findById(orderId);

        assertThat(foundOrder).isPresent();
        assertThat(foundOrder.get()).isEqualTo(order);
    }

    @Test
    void shouldCheckIfOrderExistsById() {
        Long orderId = 1L;
        when(orderRepository.existsById(orderId)).thenReturn(true);

        boolean exists = orderGateway.existsById(orderId);

        assertThat(exists).isTrue();
    }
}
