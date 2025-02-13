package com.fiap.order.management.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.order.management.domain.ItemDomain;
import com.fiap.order.management.domain.OrderDomain;
import com.fiap.order.management.dto.CustomerDTO;
import com.fiap.order.management.entity.Item;
import com.fiap.order.management.entity.Order;
import com.fiap.order.management.gateway.*;
import com.fiap.order.management.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")
class OrderServiceImplTest {

    private OrderGateway orderGateway;
    private CustomerGateway customerGateway;
    private ProductGateway productGateway;
    private StockGateway stockGateway;
    private LogisticGateway logisticGateway;
    private ObjectMapper objectMapper;
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        orderGateway = Mockito.mock(OrderGateway.class);
        customerGateway = Mockito.mock(CustomerGateway.class);
        productGateway = Mockito.mock(ProductGateway.class);
        stockGateway = Mockito.mock(StockGateway.class);
        logisticGateway = Mockito.mock(LogisticGateway.class);
        objectMapper = new ObjectMapper();
        orderService = new OrderServiceImpl(orderGateway, customerGateway, productGateway, stockGateway, logisticGateway, objectMapper);
    }

    @Test
    void shouldCreateOrder() {
        OrderDomain orderDomain = new OrderDomain(1L, Collections.singletonList(new ItemDomain(null, 1L, BigDecimal.ONE)));
        Order orderEntity = new Order();
        orderEntity.setCustomerId(1L); // Certifique-se de que o ID do cliente está definido no Order
      orderEntity.setItems(Arrays.asList(
        new Item(1L, 1L, BigDecimal.valueOf(10)),
        new Item(2L, 2L, BigDecimal.valueOf(20)),
        new Item(3L, 3L, BigDecimal.valueOf(30))
      ));
        when(orderGateway.save(any(Order.class))).thenReturn(orderEntity);
        when(customerGateway.findById(1L)).thenReturn(new CustomerDTO(1L, "John Doe", "SP", "123 Main St"));
        when(productGateway.findById(1L)).thenReturn(ResponseEntity.ok(BigDecimal.TEN));

        OrderDomain createdOrder = orderService.create(orderDomain);

        assertThat(createdOrder).isNotNull();
        verify(orderGateway).save(any(Order.class));
        verify(logisticGateway).sendOrderToDelivery(anyString());
    }
}
