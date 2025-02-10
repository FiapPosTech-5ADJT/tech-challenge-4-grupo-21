package com.fiap.order.management.dto;

import java.math.BigDecimal;

public record StockProductUpdateRequestDTO(
        Long productId,
        BigDecimal quantity
) {
}
