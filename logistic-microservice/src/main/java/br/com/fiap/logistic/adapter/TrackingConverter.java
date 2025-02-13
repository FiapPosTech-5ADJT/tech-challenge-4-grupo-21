package br.com.fiap.logistic.adapter;

import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.dto.TrackingDTO;
import br.com.fiap.logistic.entity.TrackingEntity;
import lombok.AllArgsConstructor;

import java.util.List;

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

    public TrackingDTO convertToDTO(Tracking tracking) {
        return new TrackingDTO(
                tracking.getId(),
                tracking.getOrder().getId(),
                tracking.getDeliveryPerson().getId(),
                tracking.getLatitude(),
                tracking.getLongitude(),
                tracking.getTrackingTime(),
                tracking.getCreatedAt(),
                tracking.getEstimatedDeliveryTime()
        );
    }

    public List<TrackingDTO> convertToDTOList(List<Tracking> trackingDTOList) {
        return trackingDTOList.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
