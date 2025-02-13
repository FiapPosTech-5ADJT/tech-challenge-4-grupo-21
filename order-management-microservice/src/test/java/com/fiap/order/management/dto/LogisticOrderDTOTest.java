package com.fiap.order.management.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class LogisticOrderDTOTest {

    @Test
    void shouldCreateLogisticOrderDTOWithValidData() {
        Long orderId = 1L;
        Long customerId = 2L;
        ItemDTO itemDTO = new ItemDTO(3L, BigDecimal.ONE);
        List<ItemDTO> items = List.of(itemDTO);

        LogisticOrderDTO logisticOrderDTO = new LogisticOrderDTO(orderId, customerId, items);

        assertThat(logisticOrderDTO.getOrderId()).isEqualTo(orderId);
        assertThat(logisticOrderDTO.getCustomerId()).isEqualTo(customerId);
        assertThat(logisticOrderDTO.getItems()).isEqualTo(items);
    }

    @Test
    void shouldSetAndGetFields() {
        LogisticOrderDTO logisticOrderDTO = new LogisticOrderDTO();

        logisticOrderDTO.setOrderId(1L);
        logisticOrderDTO.setCustomerId(2L);
        ItemDTO itemDTO = new ItemDTO(3L, BigDecimal.ONE);
        logisticOrderDTO.setItems(List.of(itemDTO));

        assertThat(logisticOrderDTO.getOrderId()).isEqualTo(1L);
        assertThat(logisticOrderDTO.getCustomerId()).isEqualTo(2L);
        assertThat(logisticOrderDTO.getItems()).isEqualTo(List.of(itemDTO));
    }
}
