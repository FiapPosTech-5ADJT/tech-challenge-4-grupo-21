package com.fiap.order.management.domain;

  import java.util.List;

  public class OrderDomain {
      private final Long customerId;
      private final List<ItemDomain> items;
      private StatusOrder status;

      public OrderDomain(Long customerId, List<ItemDomain> items) {
          this.customerId = customerId;
          this.items = items;
          this.status = StatusOrder.PENDENTE; // Default status
          validate();
      }

      public Long getCustomerId() {
          return customerId;
      }

      public List<ItemDomain> getItems() {
          return items;
      }

      public StatusOrder getStatus() {
          return status;
      }

      public void setStatus(StatusOrder status) {
          this.status = status;
      }

      public void validate() {
          if (this.customerId == null) {
              throw new UnsupportedOperationException("Pedidos devem ter um id de cliente.");
          }

          if (this.items == null || this.items.isEmpty()) {
              throw new UnsupportedOperationException("Pedidos devem ter pelo menos um produto.");
          }
      }
  }
