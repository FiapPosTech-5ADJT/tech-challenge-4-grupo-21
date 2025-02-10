package br.com.fiap.logistic.service.impl;

import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.gateway.TrackingGateway;
import br.com.fiap.logistic.service.TrackingService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrackingServiceImpl implements TrackingService {

    private final TrackingGateway trackingGateway;

    @Override
    public Tracking createTracking(Tracking tracking) {
        return null;
    }

    @Override
    public void updateTrackingLatitudeAndLongitude(Long trackingId, Double latitude, Double longitude) {

    }

    @Override
    public Tracking getTrackingByOrderId(Long orderId) {
        return null;
    }

    @Override
    public Tracking getTrackingByLatitudeAndLongitude(Double latitude, Double longitude) {
        return null;
    }
}
