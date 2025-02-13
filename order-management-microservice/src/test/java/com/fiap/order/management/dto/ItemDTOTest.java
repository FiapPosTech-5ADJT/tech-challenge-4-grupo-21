package com.fiap.order.management.dto;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

class ItemDTOTest {

    @Test
    void shouldCreateItemDTOWithValidData() {
        Long productId = 1L;
        BigDecimal quantity = BigDecimal.ONE;

        ItemDTO itemDTO = new ItemDTO(productId, quantity);

        assertThat(itemDTO.getProductId()).isEqualTo(productId);
        assertThat(itemDTO.getQuantity()).isEqualTo(quantity);
    }

    @Test
    void shouldSetAndGetFields() {
        ItemDTO itemDTO = new ItemDTO(1L, BigDecimal.ONE);

        itemDTO.setProductId(2L);
        itemDTO.setQuantity(BigDecimal.TEN);

        assertThat(itemDTO.getProductId()).isEqualTo(2L);
        assertThat(itemDTO.getQuantity()).isEqualTo(BigDecimal.TEN);
    }
}
