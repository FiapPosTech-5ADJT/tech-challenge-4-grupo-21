package br.com.fiap.logistic.usecase;

import br.com.fiap.logistic.service.TrackingService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class UpdateTrackingLatitudeAndLongitudeUseCase {

    private final TrackingService trackingService;

    public void update(Long trackingId, Double latitude, Double longitude) {
        trackingService.updateTrackingLatitudeAndLongitude(trackingId,
                BigDecimal.valueOf(latitude),
                BigDecimal.valueOf(longitude));
    }
}
