package br.com.fiap.logistic.service.impl;

import br.com.fiap.logistic.adapter.TrackingConverter;
import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.entity.TrackingEntity;
import br.com.fiap.logistic.gateway.TrackingGateway;
import br.com.fiap.logistic.service.TrackingService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TrackingServiceImpl implements TrackingService {

    private final TrackingGateway trackingGateway;
    private final TrackingConverter trackingConverter;

    @Override
    public Tracking createTracking(Tracking tracking) {
        final TrackingEntity trackingEntity = trackingConverter.toEntity(tracking);
        return trackingConverter.toDomain(trackingGateway.createTracking(trackingEntity));
    }

    @Override
    public void updateTrackingLatitudeAndLongitude(Long trackingId, BigDecimal latitude, BigDecimal longitude) {
        if (latitude == null || longitude == null) {
            throw new RuntimeException("Latitude e longitude são obrigatórios");
        }
        final Tracking tracking = getTrackingByOrderId(trackingId);
        tracking.updateLatitudeLongitude(latitude, longitude);
        trackingGateway.updateTrackingLatitudeAndLongitude(trackingConverter.toEntity(tracking));
    }

    @Override
    public Tracking getTrackingByOrderId(Long orderId) {
        final TrackingEntity trackingEntity = trackingGateway.getTrackingByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Tracking não encontrado"));
        return trackingConverter.toDomain(trackingEntity);
    }

    @Override
    public List<Tracking> getTrackingByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude) {
        return trackingGateway.getTrackingByLatitudeAndLongitude(latitude, longitude)
                .map(list -> list.stream().map(trackingConverter::toDomain).collect(Collectors.toList()))
                .orElseThrow( () -> new RuntimeException("Tracking não encontrado"));
    }
}
