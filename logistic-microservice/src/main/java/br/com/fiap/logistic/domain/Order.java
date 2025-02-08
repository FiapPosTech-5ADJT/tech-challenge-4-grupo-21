package br.com.fiap.logistic.domain;

import java.time.LocalDateTime;

public class Order {

    private Long id;
    private Long customerId;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime estimatedDelivery;
    private LocalDateTime deliveredAt;

    public Order(Long id,
                 Long customerId,
                 LocalDateTime estimatedDelivery,
                 LocalDateTime deliveredAt) {
        this.id = id;
        this.customerId = customerId;
        this.status = OrderStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.estimatedDelivery = estimatedDelivery;
        this.deliveredAt = deliveredAt;
    }

    public Order(Long customerId,
                 LocalDateTime estimatedDelivery) {
        this.customerId = customerId;
        this.status = OrderStatus.PENDING;
        this.estimatedDelivery = estimatedDelivery;
        this.createdAt = LocalDateTime.now();
    }

    public void setStatus(OrderStatus status) {
        if (validateStatusOrder(status)) {
            this.status = status;
        }
    }

    private boolean validateStatusOrder(OrderStatus newStatus) {
        if (isOrderCompleted()) {
            throw new IllegalArgumentException("Order already completed");
        } else if (isOrderCanceled()) {
            throw new IllegalArgumentException("Order already canceled");
        } else if (isOrderPending() && newStatus == OrderStatus.COMPLETED) {
            throw new IllegalArgumentException("Order can't be completed without being in transit");
        } else if (isOrderInTransit() && newStatus == OrderStatus.PENDING) {
            throw new IllegalArgumentException("Order can't be pending after being in transit");
        }
        return true;
    }

    private boolean isOrderCompleted() {
        return this.status == OrderStatus.COMPLETED;
    }

    private boolean isOrderCanceled() {
        return this.status == OrderStatus.CANCELED;
    }

    private boolean isOrderPending() {
        return this.status == OrderStatus.PENDING;
    }

    private boolean isOrderInTransit() {
        return this.status == OrderStatus.IN_TRANSIT;
    }


    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }
}
