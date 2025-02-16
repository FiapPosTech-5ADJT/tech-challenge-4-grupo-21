package br.com.fiap.logistic.gateway;

import br.com.fiap.logistic.entity.TrackingEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TrackingGateway {

    TrackingEntity createTracking(TrackingEntity tracking);

    void updateTrackingLatitudeAndLongitude(TrackingEntity tracking);

    Optional<TrackingEntity> getTrackingByOrderId(Long orderId);

    Optional<List<TrackingEntity>> getTrackingByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

}
