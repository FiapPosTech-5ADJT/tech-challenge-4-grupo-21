package com.fiap.order.management.mapper;

import com.fiap.order.management.adapter.mapper.OrderMapper;
import com.fiap.order.management.domain.ItemDomain;
import com.fiap.order.management.domain.OrderDomain;
import com.fiap.order.management.dto.LogisticOrderDTO;
import com.fiap.order.management.entity.Item;
import com.fiap.order.management.entity.Order;
import com.fiap.order.management.domain.StatusOrder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderMapperTest {

    @Test
    void toModel_shouldMapOrderDomainToOrder() {
        // Arrange
        ItemDomain itemDomain = new ItemDomain(1L, 1L, BigDecimal.ONE);
        OrderDomain orderDomain = new OrderDomain(1L, List.of(itemDomain));
        orderDomain.setStatus(StatusOrder.PENDENTE);

        // Act
        Order order = OrderMapper.toModel(orderDomain);

        // Assert
        assertEquals(orderDomain.getCustomerId(), order.getCustomerId());
        assertEquals(orderDomain.getStatus(), order.getStatus());
        assertEquals(orderDomain.getItems().size(), order.getItems().size());
    }

    @Test
    void toDomain_shouldMapOrderToOrderDomain() {
        // Arrange
        Item item = new Item(1L, 1L, BigDecimal.ONE);
        Order order = new Order(1L, 1L, StatusOrder.PENDENTE, List.of(item));

        // Act
        OrderDomain orderDomain = OrderMapper.toDomain(order);

        // Assert
        assertEquals(order.getCustomerId(), orderDomain.getCustomerId());
        assertEquals(order.getStatus(), orderDomain.getStatus());
        assertEquals(order.getItems().size(), orderDomain.getItems().size());
    }

    @Test
    void toLogisticOrderDTO_shouldMapOrderToLogisticOrderDTO() {
        // Arrange
        Item item = new Item(1L, 1L, BigDecimal.ONE);
        Order order = new Order(1L, 1L, StatusOrder.PENDENTE, List.of(item));

        // Act
        LogisticOrderDTO logisticOrderDTO = OrderMapper.toLogisticOrderDTO(order);

        // Assert
        assertEquals(order.getId(), logisticOrderDTO.getOrderId());
        assertEquals(order.getCustomerId(), logisticOrderDTO.getCustomerId());
        assertEquals(order.getItems().size(), logisticOrderDTO.getItems().size());
    }
}
