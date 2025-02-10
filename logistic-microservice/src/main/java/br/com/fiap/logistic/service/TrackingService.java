package br.com.fiap.logistic.service;

import br.com.fiap.logistic.domain.Tracking;

public interface TrackingService {

    Tracking createTracking(Tracking tracking);

    void updateTrackingLatitudeAndLongitude(Long trackingId, Double latitude, Double longitude);

    Tracking getTrackingByOrderId(Long orderId);

    Tracking getTrackingByLatitudeAndLongitude(Double latitude, Double longitude);
}
