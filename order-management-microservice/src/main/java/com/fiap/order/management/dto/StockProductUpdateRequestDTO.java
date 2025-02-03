package com.fiap.order.management.dto;

import java.math.BigInteger;

public record StockProductUpdateRequestDTO(
        String productId,
        BigInteger quantity,
        StockActionEnum action
) {
}
