package com.fiap.order.management.mapper;

import com.fiap.order.management.adapter.OrderConverter;
import com.fiap.order.management.domain.ItemDomain;
import com.fiap.order.management.domain.OrderDomain;
import com.fiap.order.management.dto.ItemDTO;
import com.fiap.order.management.dto.OrderDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderConverterTest {

    @Test
    void toDomain_shouldMapOrderDTOToOrderDomain() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO(1L, BigDecimal.ONE);
        OrderDTO orderDTO = new OrderDTO(1L, List.of(itemDTO));

        // Act
        OrderDomain orderDomain = OrderConverter.toDomain(orderDTO);

        // Assert
        assertEquals(orderDTO.getCustomerId(), orderDomain.getCustomerId());
        assertEquals(orderDTO.getItems().size(), orderDomain.getItems().size());
    }

    @Test
    void toDTO_shouldMapOrderDomainToOrderDTO() {
        // Arrange
        ItemDomain itemDomain = new ItemDomain(null, 1L, BigDecimal.ONE);
        OrderDomain orderDomain = new OrderDomain(1L, List.of(itemDomain));

        // Act
        OrderDTO orderDTO = OrderConverter.toDTO(orderDomain);

        // Assert
        assertEquals(orderDomain.getCustomerId(), orderDTO.getCustomerId());
        assertEquals(orderDomain.getItems().size(), orderDTO.getItems().size());
    }
}
