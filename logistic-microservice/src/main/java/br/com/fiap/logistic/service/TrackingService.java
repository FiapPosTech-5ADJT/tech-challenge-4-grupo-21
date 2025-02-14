package br.com.fiap.logistic.service;

import br.com.fiap.logistic.domain.Tracking;

import java.math.BigDecimal;
import java.util.List;

public interface TrackingService {

    Tracking createTracking(Tracking tracking);

    void updateTrackingLatitudeAndLongitude(Long trackingId, BigDecimal latitude, BigDecimal longitude);

    Tracking getTrackingByOrderId(Long orderId);

    List<Tracking> getTrackingByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);
}
