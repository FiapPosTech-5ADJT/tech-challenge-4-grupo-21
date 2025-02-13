package com.fiap.order.management.entity;

import com.fiap.order.management.domain.StatusOrder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void shouldCreateOrderWithValidData() {
        Long id = 1L;
        Long customerId = 2L;
        StatusOrder status = StatusOrder.PENDENTE;
        Item item = new Item(3L, 4L, BigDecimal.TEN);
        List<Item> items = List.of(item);

        Order order = new Order(id, customerId, status, items);

        assertThat(order.getId()).isEqualTo(id);
        assertThat(order.getCustomerId()).isEqualTo(customerId);
        assertThat(order.getStatus()).isEqualTo(status);
        assertThat(order.getItems()).isEqualTo(items);
    }

    @Test
    void shouldSetAndGetFields() {
        Order order = new Order();

        order.setId(1L);
        order.setCustomerId(2L);
        order.setStatus(StatusOrder.PENDENTE);
        Item item = new Item(3L, 4L, BigDecimal.TEN);
        order.setItems(List.of(item));

        assertThat(order.getId()).isEqualTo(1L);
        assertThat(order.getCustomerId()).isEqualTo(2L);
        assertThat(order.getStatus()).isEqualTo(StatusOrder.PENDENTE);
        assertThat(order.getItems()).isEqualTo(List.of(item));
    }
}
