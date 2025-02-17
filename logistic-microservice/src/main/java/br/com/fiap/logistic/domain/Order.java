package br.com.fiap.logistic.domain;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Order {

    private Long id;
    private Long orderExternalId;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime estimatedDelivery;
    private LocalDateTime deliveredAt;
    private String zipCode;
    private Long deliveryPersonId;

    public Order(Long id,
                 Long orderExternalId,
                 LocalDateTime estimatedDelivery,
                 LocalDateTime deliveredAt,
                 String zipCode,
                 Long deliveryPersonId) {
        this.id = id;
        this.orderExternalId = orderExternalId;
        this.setZipCode(zipCode);
        this.status = OrderStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.estimatedDelivery = estimatedDelivery;
        this.deliveredAt = deliveredAt;
        this.deliveryPersonId = deliveryPersonId;
    }

    public Order(Long orderExternalId,
                 LocalDateTime estimatedDelivery,
                 String zipCode,
                 Long deliveryPersonId) {
        this.orderExternalId = orderExternalId;
        this.setZipCode(zipCode);
        this.status = OrderStatus.PENDING;
        this.estimatedDelivery = estimatedDelivery;
        this.createdAt = LocalDateTime.now();
        this.deliveryPersonId = deliveryPersonId;
    }

    @SuppressWarnings("java:S107")
    public Order(Long id, Long orderExternalId,
                 OrderStatus status,
                 LocalDateTime createdAt,
                 LocalDateTime estimatedDelivery,
                 LocalDateTime deliveredAt,
                 String zipCode,
                 Long deliveryPersonId) {
        this.id = id;
        this.orderExternalId = orderExternalId;
        this.status = status;
        this.createdAt = createdAt;
        this.estimatedDelivery = estimatedDelivery;
        this.deliveredAt = deliveredAt;
        this.setZipCode(zipCode);
        this.deliveryPersonId = deliveryPersonId;
    }

    public void setStatus(OrderStatus status) {
        this.validateStatusOrder(status);
        this.status = status;
    }

    public void setDeliveryPersonId(Long deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public Long getDeliveryPersonId() {
        return deliveryPersonId;
    }

    private void validateStatusOrder(OrderStatus newStatus) {
        if (isOrderCompleted()) {
            throw new IllegalArgumentException("Order already completed");
        } else if (isOrderCanceled()) {
            throw new IllegalArgumentException("Order already canceled");
        } else if (isOrderPending() && newStatus == OrderStatus.COMPLETED) {
            throw new IllegalArgumentException("Order can't be completed without being in transit");
        } else if (isOrderInTransit() && newStatus == OrderStatus.PENDING) {
            throw new IllegalArgumentException("Order can't be pending after being in transit");
        }
    }

    private void setZipCode(String zipCode) {
        if (!Pattern.matches("\\d{5}-?\\d{3}", zipCode)) {
            throw new IllegalArgumentException("Invalid zip code");
        }
        this.zipCode = zipCode;
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

    public Long getOrderExternalId() {
        return orderExternalId;
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

    public String getZipCode() {
        return zipCode;
    }
}
