package com.fiap.order.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
@AllArgsConstructor
@Getter
public class ItemDTO {
  private Long productId;
  private BigDecimal quantity;
}
