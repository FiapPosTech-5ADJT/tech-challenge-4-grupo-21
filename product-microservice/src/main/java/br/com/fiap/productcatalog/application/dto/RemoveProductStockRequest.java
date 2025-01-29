package br.com.fiap.productcatalog.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RemoveProductStockRequest(@NotNull @Positive Long productId, @NotNull @Positive BigDecimal quantity) {
}
