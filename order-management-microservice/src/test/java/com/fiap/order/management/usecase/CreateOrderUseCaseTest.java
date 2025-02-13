package com.fiap.order.management.usecase;

import com.fiap.order.management.domain.ItemDomain;
import com.fiap.order.management.domain.OrderDomain;
import com.fiap.order.management.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateOrderUseCaseTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldCreateOrder() {
        // Arrange
        OrderDomain orderDomain =  new OrderDomain(1L, Collections.singletonList(new ItemDomain(null, 1L, BigDecimal.ONE)));
        OrderDomain createdOrder =  new OrderDomain(1L, Collections.singletonList(new ItemDomain(null, 1L, BigDecimal.ONE)));
        when(orderService.create(any(OrderDomain.class))).thenReturn(createdOrder);

        // Act
        OrderDomain result = createOrderUseCase.execute(orderDomain);

        // Assert
        assertEquals(createdOrder, result);
    }
}
