package com.fiap.order.management.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    void shouldCreateItemWithValidData() {
        Long id = 1L;
        Long productId = 2L;
        BigDecimal quantity = BigDecimal.TEN;

        Item item = new Item(id, productId, quantity);

        assertThat(item.getId()).isEqualTo(id);
        assertThat(item.getProductId()).isEqualTo(productId);
        assertThat(item.getQuantity()).isEqualTo(quantity);
    }

    @Test
    void shouldSetAndGetFields() {
        Item item = new Item();

        item.setId(1L);
        item.setProductId(2L);
        item.setQuantity(BigDecimal.TEN);

        assertThat(item.getId()).isEqualTo(1L);
        assertThat(item.getProductId()).isEqualTo(2L);
        assertThat(item.getQuantity()).isEqualTo(BigDecimal.TEN);
    }
}
