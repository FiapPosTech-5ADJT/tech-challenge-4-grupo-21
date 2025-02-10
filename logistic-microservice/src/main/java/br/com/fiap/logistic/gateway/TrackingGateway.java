package br.com.fiap.logistic.gateway;

import br.com.fiap.logistic.domain.Tracking;

public interface TrackingGateway {

    Tracking createTracking(Tracking tracking);

    void updateTrackingLatitudeAndLongitude(Long trackingId, Double latitude, Double longitude);

    Tracking getTrackingByOrderId(Long orderId);

    Tracking getTrackingByLatitudeAndLongitude(Double latitude, Double longitude);
}
