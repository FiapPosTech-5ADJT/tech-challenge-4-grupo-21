package br.com.fiap.logistic.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Tracking {

    private Long id;
    private Long orderId;
    private Long deliveryPersonId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime trackingTime;
    private LocalDateTime createdAt;
    private LocalDateTime estimatedDeliveryTime;

    @SuppressWarnings("java:S107")
    public Tracking(Long id,
                    Long orderId,
                    Long deliveryPersonId,
                    BigDecimal latitude,
                    BigDecimal longitude,
                    LocalDateTime trackingTime,
                    LocalDateTime createdAt,
                    LocalDateTime estimatedDeliveryTime) {
        this.id = id;
        this.orderId = orderId;
        this.deliveryPersonId = deliveryPersonId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trackingTime = trackingTime;
        this.createdAt = createdAt;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public Tracking(Long orderId, Long deliveryPersonId, LocalDateTime estimatedDeliveryTime) {
        this.orderId = orderId;
        this.deliveryPersonId = deliveryPersonId;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getDeliveryPersonId() {
        return deliveryPersonId;
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
        if (estimatedDeliveryTime == null) {
            throw new IllegalStateException("A data de entrega estimada não pode ser nula");
        }

        if (estimatedDeliveryTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de entrega estimada não pode ser no passado");
        }
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }
}
