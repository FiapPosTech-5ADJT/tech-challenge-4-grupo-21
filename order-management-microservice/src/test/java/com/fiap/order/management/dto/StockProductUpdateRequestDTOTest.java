package com.fiap.order.management.dto;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

class StockProductUpdateRequestDTOTest {

    @Test
    void shouldCreateStockProductUpdateRequestDTOWithValidData() {
        Long productId = 1L;
        BigDecimal quantity = BigDecimal.TEN;

        StockProductUpdateRequestDTO dto = new StockProductUpdateRequestDTO(productId, quantity);

        assertThat(dto.productId()).isEqualTo(productId);
        assertThat(dto.quantity()).isEqualTo(quantity);
    }
}
