package com.fiap.order.management.domain;

        import java.math.BigInteger;
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

                if (!hasValidQuantities()) {
                    throw new UnsupportedOperationException(
                            "Os produtos de um pedido devem estar com uma quantidade acima de zero."
                    );
                }
            }

            private boolean hasValidQuantities() {
                return this.items.stream()
                        .noneMatch(item -> item.getQuantity().compareTo(BigInteger.ZERO) <= 0);
            }
        }
