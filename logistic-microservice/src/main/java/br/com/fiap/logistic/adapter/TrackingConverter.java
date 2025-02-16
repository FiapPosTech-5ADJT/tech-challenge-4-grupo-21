package br.com.fiap.logistic.adapter;

import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.dto.TrackingDTO;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import br.com.fiap.logistic.entity.OrderEntity;
import br.com.fiap.logistic.entity.TrackingEntity;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TrackingConverter {

    public TrackingEntity toEntity(Tracking tracking) {
        return TrackingEntity.builder()
                .id(tracking.getId())
                .order(tracking.getOrderId() != null
                        ? OrderEntity.builder().id(tracking.getOrderId()).build()
                        : null)
                .deliveryPerson(tracking.getDeliveryPersonId() != null
                        ? DeliveryPersonEntity.builder().id(tracking.getDeliveryPersonId()).build()
                        : null)
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
                trackingEntity.getOrder() != null ? trackingEntity.getOrder().getId() : null,
                trackingEntity.getDeliveryPerson() != null ? trackingEntity.getDeliveryPerson().getId() : null,
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
                tracking.getOrderId(),
                tracking.getDeliveryPersonId(),
                tracking.getLatitude(),
                tracking.getLongitude(),
                tracking.getTrackingTime(),
                tracking.getCreatedAt(),
                tracking.getEstimatedDeliveryTime()
        );
    }

    public List<TrackingDTO> convertToDTOList(List<Tracking> trackingList) {
        return trackingList.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
