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

    public Tracking(Long id, Long orderId, Long deliveryPersonId, BigDecimal latitude, BigDecimal longitude, LocalDateTime trackingTime) {
        this.id = id;
        this.orderId = orderId;
        this.deliveryPersonId = deliveryPersonId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trackingTime = trackingTime;
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

    public void updateLatitudeLongitude(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.trackingTime = LocalDateTime.now();
    }
}
