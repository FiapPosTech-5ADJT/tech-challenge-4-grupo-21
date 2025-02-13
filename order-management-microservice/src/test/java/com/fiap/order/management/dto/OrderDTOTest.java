package com.fiap.order.management.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class OrderDTOTest {

    @Test
    void shouldCreateOrderDTOWithValidData() {
        Long customerId = 1L;
        ItemDTO itemDTO = new ItemDTO(2L, BigDecimal.ONE);
        List<ItemDTO> items = List.of(itemDTO);

        OrderDTO orderDTO = new OrderDTO(customerId, items);

        assertThat(orderDTO.getCustomerId()).isEqualTo(customerId);
        assertThat(orderDTO.getItems()).isEqualTo(items);
    }

    @Test
    void shouldSetAndGetFields() {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setCustomerId(1L);
        ItemDTO itemDTO = new ItemDTO(2L, BigDecimal.ONE);
        orderDTO.setItems(List.of(itemDTO));

        assertThat(orderDTO.getCustomerId()).isEqualTo(1L);
        assertThat(orderDTO.getItems()).isEqualTo(List.of(itemDTO));
    }
}
