package com.fiap.order.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
@AllArgsConstructor
@Getter
public class ItemDTO {
  private String productId;
  private BigInteger quantity;
}
