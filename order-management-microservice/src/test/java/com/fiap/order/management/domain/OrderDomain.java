package com.fiap.order.management.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

class OrderDomainTest {

  @Test
  void shouldCreateOrderWithValidData() {
    Long customerId = 1L;
    List<ItemDomain> items = List.of(new ItemDomain(null, 1L, BigDecimal.ONE));

    OrderDomain order = new OrderDomain(customerId, items);

    assertThat(order.getCustomerId()).isEqualTo(customerId);
    assertThat(order.getItems()).isEqualTo(items);
    assertThat(order.getStatus()).isEqualTo(StatusOrder.PENDENTE);
  }

  @Test
  void shouldThrowExceptionWhenCustomerIdIsNull() {
    List<ItemDomain> items = List.of(new ItemDomain(null, 1L, BigDecimal.ONE));

    assertThatThrownBy(() -> new OrderDomain(null, items))
      .isInstanceOf(UnsupportedOperationException.class)
      .hasMessage("Pedidos devem ter um id de cliente.");
  }

  @Test
  void shouldThrowExceptionWhenItemsAreNull() {
    Long customerId = 1L;

    assertThatThrownBy(() -> new OrderDomain(customerId, null))
      .isInstanceOf(UnsupportedOperationException.class)
      .hasMessage("Pedidos devem ter pelo menos um produto.");
  }

  @Test
  void shouldThrowExceptionWhenItemsAreEmpty() {
    Long customerId = 1L;

    assertThatThrownBy(() -> new OrderDomain(customerId, Collections.emptyList()))
      .isInstanceOf(UnsupportedOperationException.class)
      .hasMessage("Pedidos devem ter pelo menos um produto.");
  }
}
