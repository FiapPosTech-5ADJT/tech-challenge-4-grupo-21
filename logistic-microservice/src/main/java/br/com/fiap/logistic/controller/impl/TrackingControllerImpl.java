package br.com.fiap.logistic.controller.impl;

import br.com.fiap.logistic.adapter.TrackingConverter;
import br.com.fiap.logistic.controller.TrackingController;
import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.dto.TrackingDTO;
import br.com.fiap.logistic.usecase.GetTrackingByLatitudeAndLongitudeUseCase;
import br.com.fiap.logistic.usecase.UpdateTrackingLatitudeAndLongitudeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class TrackingControllerImpl implements TrackingController {

    private final UpdateTrackingLatitudeAndLongitudeUseCase updateTrackingLatitudeAndLongitudeUseCase;
    private final GetTrackingByLatitudeAndLongitudeUseCase getTrackingByLatitudeAndLongitudeUseCase;
    private final TrackingConverter trackingConverter;

    @Override
    public ResponseEntity<Void> updateTrackingLatitudeAndLongitude(Long trackingId, Double latitude, Double longitude) {
        updateTrackingLatitudeAndLongitudeUseCase.update(trackingId, latitude, longitude);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<TrackingDTO>> getTrackingByLatitudeAndLongitude(Double latitude, Double longitude) {
        List<Tracking> trackingDTOList = getTrackingByLatitudeAndLongitudeUseCase.getTrackingByLatitudeAndLongitude(latitude, longitude);
        return ResponseEntity.ok(trackingConverter.convertToDTOList(trackingDTOList));
    }
}
