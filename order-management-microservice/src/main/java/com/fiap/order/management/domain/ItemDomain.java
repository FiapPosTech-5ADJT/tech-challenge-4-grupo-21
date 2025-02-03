package com.fiap.order.management.domain;

import java.math.BigInteger;

public class ItemDomain {
  private Long id;
  private String productId;
  private BigInteger quantity;

  public ItemDomain(Long id, String productId, BigInteger quantity) {
    this.id = id;
    this.productId = productId;
    this.quantity = quantity;
    validateQuantity();
  }

  public Long getId() {
    return id;
  }

  public String getProductId() {
    return productId;
  }

  public BigInteger getQuantity() {
    return quantity;
  }

  public void setQuantity(BigInteger quantity) {
    this.quantity = quantity;
    validateQuantity();
  }

  private void validateQuantity() {
    if (this.quantity == null || this.quantity.compareTo(BigInteger.ZERO) <= 0) {
      throw new UnsupportedOperationException("A quantidade deve ser maior que zero.");
    }
  }
}
