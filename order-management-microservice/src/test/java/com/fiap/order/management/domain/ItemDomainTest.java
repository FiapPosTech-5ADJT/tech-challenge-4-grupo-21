package com.fiap.order.management.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

class ItemDomainTest {

  @Test
  void shouldCreateItemWithValidData() {
    Long id = 1L;
    Long productId = 1L;
    BigDecimal quantity = BigDecimal.ONE;

    ItemDomain item = new ItemDomain(id, productId, quantity);

    assertThat(item.getId()).isEqualTo(id);
    assertThat(item.getProductId()).isEqualTo(productId);
    assertThat(item.getQuantity()).isEqualTo(quantity);
  }

  @Test
  void shouldThrowExceptionWhenQuantityIsNull() {
    Long id = 1L;
    Long productId = 1L;

    assertThatThrownBy(() -> new ItemDomain(id, productId, null))
      .isInstanceOf(UnsupportedOperationException.class)
      .hasMessage("A quantidade deve ser maior que zero.");
  }

  @Test
  void shouldThrowExceptionWhenQuantityIsZero() {
    Long id = 1L;
    Long productId = 1L;
    BigDecimal quantity = BigDecimal.ZERO;

    assertThatThrownBy(() -> new ItemDomain(id, productId, quantity))
      .isInstanceOf(UnsupportedOperationException.class)
      .hasMessage("A quantidade deve ser maior que zero.");
  }

  @Test
  void shouldThrowExceptionWhenQuantityIsNegative() {
    Long id = 1L;
    Long productId = 1L;
    BigDecimal quantity = BigDecimal.valueOf(-1);

    assertThatThrownBy(() -> new ItemDomain(id, productId, quantity))
      .isInstanceOf(UnsupportedOperationException.class)
      .hasMessage("A quantidade deve ser maior que zero.");
  }

  @Test
  void shouldUpdateQuantityWithValidData() {
    Long id = 1L;
    Long productId = 1L;
    BigDecimal initialQuantity = BigDecimal.ONE;
    BigDecimal newQuantity = BigDecimal.TEN;

    ItemDomain item = new ItemDomain(id, productId, initialQuantity);
    item.setQuantity(newQuantity);

    assertThat(item.getQuantity()).isEqualTo(newQuantity);
  }

  @Test
  void shouldThrowExceptionWhenUpdatingQuantityToNull() {
    Long id = 1L;
    Long productId = 1L;
    BigDecimal initialQuantity = BigDecimal.ONE;

    ItemDomain item = new ItemDomain(id, productId, initialQuantity);

    assertThatThrownBy(() -> item.setQuantity(null))
      .isInstanceOf(UnsupportedOperationException.class)
      .hasMessage("A quantidade deve ser maior que zero.");
  }

  @Test
  void shouldThrowExceptionWhenUpdatingQuantityToZero() {
    Long id = 1L;
    Long productId = 1L;
    BigDecimal initialQuantity = BigDecimal.ONE;

    ItemDomain item = new ItemDomain(id, productId, initialQuantity);

    assertThatThrownBy(() -> item.setQuantity(BigDecimal.ZERO))
      .isInstanceOf(UnsupportedOperationException.class)
      .hasMessage("A quantidade deve ser maior que zero.");
  }

  @Test
  void shouldThrowExceptionWhenUpdatingQuantityToNegative() {
    Long id = 1L;
    Long productId = 1L;
    BigDecimal initialQuantity = BigDecimal.ONE;

    ItemDomain item = new ItemDomain(id, productId, initialQuantity);

    assertThatThrownBy(() -> item.setQuantity(BigDecimal.valueOf(-1)))
      .isInstanceOf(UnsupportedOperationException.class)
      .hasMessage("A quantidade deve ser maior que zero.");
  }
}
