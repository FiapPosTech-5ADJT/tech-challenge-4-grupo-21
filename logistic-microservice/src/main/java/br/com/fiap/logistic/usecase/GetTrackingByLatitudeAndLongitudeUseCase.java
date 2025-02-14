package br.com.fiap.logistic.usecase;

import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.service.TrackingService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class GetTrackingByLatitudeAndLongitudeUseCase {

    private final TrackingService trackingService;

    public List<Tracking> getTrackingByLatitudeAndLongitude(Double latitude, Double longitude) {
        return trackingService.getTrackingByLatitudeAndLongitude(BigDecimal.valueOf(latitude), BigDecimal.valueOf(longitude));
    }
}
