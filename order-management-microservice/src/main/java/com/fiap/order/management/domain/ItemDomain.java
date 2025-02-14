package com.fiap.order.management.domain;

import java.math.BigDecimal;

public class ItemDomain {
  private Long id;
  private Long productId;
  private BigDecimal quantity;

  public ItemDomain(Long id, Long productId, BigDecimal quantity) {
    this.id = id;
    this.productId = productId;
    this.quantity = quantity;
    validateQuantity();
  }

  public Long getId() {
    return id;
  }

  public Long getProductId() {
    return productId;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
    validateQuantity();
  }

  private void validateQuantity() {
    if (this.quantity == null || this.quantity.compareTo(BigDecimal.ZERO) <= 0) {
      throw new UnsupportedOperationException("A quantidade deve ser maior que zero.");
    }
  }
}
