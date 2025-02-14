package br.com.fiap.logistic.gateway.impl;

import br.com.fiap.logistic.entity.TrackingEntity;
import br.com.fiap.logistic.gateway.TrackingGateway;
import br.com.fiap.logistic.repository.TrackingRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TrackingGatewayImpl implements TrackingGateway {

    private final TrackingRepository trackingRepository;

    @Override
    public TrackingEntity createTracking(TrackingEntity tracking) {
        return trackingRepository.save(tracking);
    }

    @Override
    public void updateTrackingLatitudeAndLongitude(TrackingEntity tracking) {
        trackingRepository.save(tracking);
    }

    @Override
    public Optional<TrackingEntity> getTrackingByOrderId(Long orderId) {
        return trackingRepository.findById(orderId);
    }

    @Override
    public Optional<List<TrackingEntity>> getTrackingByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude) {
        return trackingRepository.findByLatitudeAndLongitude(latitude, longitude);
    }
}
