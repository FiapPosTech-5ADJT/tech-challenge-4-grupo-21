package com.fiap.order.management.controller;

import com.fiap.order.management.controller.impl.OrderControllerImpl;
import com.fiap.order.management.domain.ItemDomain;
import com.fiap.order.management.domain.OrderDomain;
import com.fiap.order.management.dto.OrderDTO;
import com.fiap.order.management.dto.ItemDTO;
import com.fiap.order.management.usecase.CreateOrderUseCase;
import com.fiap.order.management.usecase.FindOrderByIdUseCase;
import com.fiap.order.management.adapter.OrderConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class OrderControllerImplTest {

    @Mock
    private CreateOrderUseCase createOrderUseCase;

    @Mock
    private FindOrderByIdUseCase findOrderByIdUseCase;

    @InjectMocks
    private OrderControllerImpl orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateOrder() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductId(1L);
        itemDTO.setQuantity(BigDecimal.ONE);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerId(1L); // Ensure customer ID is set
        orderDTO.setItems(List.of(itemDTO));

        List<ItemDomain> items = List.of(new ItemDomain(null, 1L, BigDecimal.ONE));
        OrderDomain orderDomain = new OrderDomain(1L, items);
        OrderDomain createdOrder = new OrderDomain(1L, items);

        try (MockedStatic<OrderConverter> mockedConverter = mockStatic(OrderConverter.class)) {
            mockedConverter.when(() -> OrderConverter.toDomain(orderDTO)).thenReturn(orderDomain);
            mockedConverter.when(() -> OrderConverter.toDTO(createdOrder)).thenReturn(orderDTO);

            when(createOrderUseCase.execute(any(OrderDomain.class))).thenReturn(createdOrder);

            ResponseEntity<OrderDTO> response = orderController.create(orderDTO);

            assertThat(response.getStatusCodeValue()).isEqualTo(200);
            assertThat(response.getBody()).isEqualTo(orderDTO);
        }
    }

    @Test
    void shouldFindOrderById() {
        Long orderId = 1L;
        List<ItemDomain> items = List.of(new ItemDomain(null, 1L, BigDecimal.ONE));
        OrderDomain orderDomain = new OrderDomain(orderId, items);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setItems(List.of(new ItemDTO()));

        try (MockedStatic<OrderConverter> mockedConverter = mockStatic(OrderConverter.class)) {
            mockedConverter.when(() -> OrderConverter.toDTO(orderDomain)).thenReturn(orderDTO);

            when(findOrderByIdUseCase.execute(orderId)).thenReturn(orderDomain);

            ResponseEntity<OrderDTO> response = orderController.findById(orderId);

            assertThat(response.getStatusCodeValue()).isEqualTo(200);
            assertThat(response.getBody()).isEqualTo(orderDTO);
        }
    }
}
