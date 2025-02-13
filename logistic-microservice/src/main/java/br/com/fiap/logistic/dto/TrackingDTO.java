package br.com.fiap.logistic.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TrackingDTO(
        Long id,
        Long orderId,
        Long deliveryPersonId,
        BigDecimal latitude,
        BigDecimal longitude,
        LocalDateTime trackingTime,
        LocalDateTime createdAt,
        LocalDateTime estimatedDeliveryTime
) {
}
