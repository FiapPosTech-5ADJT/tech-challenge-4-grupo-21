package br.com.fiap.logistic.adapter;

import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.entity.TrackingEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrackingConverter {

    private final OrderConverter orderConverter;
    private final DeliveryPersonConverter deliveryPersonConverter;

    public TrackingEntity toEntity(Tracking tracking) {
        return TrackingEntity.builder()
                .id(tracking.getId())
                .order(orderConverter.convertToEntity(tracking.getOrder()))
                .deliveryPerson(deliveryPersonConverter.convertToEntity(tracking.getDeliveryPerson()))
                .latitude(tracking.getLatitude())
                .longitude(tracking.getLongitude())
                .trackingTime(tracking.getTrackingTime())
                .createdAt(tracking.getCreatedAt())
                .estimatedDeliveryTime(tracking.getEstimatedDeliveryTime())
                .build();
    }

    public Tracking toDomain(TrackingEntity trackingEntity) {
        return new Tracking(
                trackingEntity.getId(),
                orderConverter.convertToDomain(trackingEntity.getOrder()),
                deliveryPersonConverter.convertToDomain(trackingEntity.getDeliveryPerson()),
                trackingEntity.getLatitude(),
                trackingEntity.getLongitude(),
                trackingEntity.getTrackingTime(),
                trackingEntity.getCreatedAt(),
                trackingEntity.getEstimatedDeliveryTime()
        );
    }
}
