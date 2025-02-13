package br.com.fiap.logistic.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Tracking {

    private Long id;
    private Order order;
    private DeliveryPerson deliveryPerson;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime trackingTime;
    private LocalDateTime createdAt;
    private LocalDateTime estimatedDeliveryTime;

    public Tracking(Long id,
                    Order order,
                    DeliveryPerson deliveryPerson,
                    BigDecimal latitude,
                    BigDecimal longitude,
                    LocalDateTime trackingTime,
                    LocalDateTime createdAt,
                    LocalDateTime estimatedDeliveryTime) {
        this.id = id;
        this.order = order;
        this.deliveryPerson = deliveryPerson;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trackingTime = trackingTime;
        this.createdAt = createdAt;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public Tracking(Order order, DeliveryPerson deliveryPerson, BigDecimal latitude, BigDecimal longitude, LocalDateTime estimatedDeliveryTime) {
        this.order = order;
        this.deliveryPerson = deliveryPerson;
        this.latitude = latitude;
        this.longitude = longitude;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public LocalDateTime getTrackingTime() {
        return trackingTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void updateLatitudeLongitude(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.trackingTime = LocalDateTime.now();
    }

    public void setEstimatedDeliveryTime(LocalDateTime estimatedDeliveryTime) {
        if (this.estimatedDeliveryTime == null) {
            throw new IllegalStateException("A data de entrega estimada não pode ser nulla");
        }

        if (estimatedDeliveryTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de entrega estimada não pode ser no passado");
        }
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }
}
