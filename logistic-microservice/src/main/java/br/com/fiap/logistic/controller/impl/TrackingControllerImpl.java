package br.com.fiap.logistic.controller.impl;

import br.com.fiap.logistic.adapter.TrackingConverter;
import br.com.fiap.logistic.controller.TrackingController;
import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.dto.TrackingDTO;
import br.com.fiap.logistic.usecase.GetTrackingByLatitudeAndLongitudeUseCase;
import br.com.fiap.logistic.usecase.UpdateTrackingLatitudeAndLongitudeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/trackings")
public class TrackingControllerImpl implements TrackingController {

    private final UpdateTrackingLatitudeAndLongitudeUseCase updateTrackingLatitudeAndLongitudeUseCase;
    private final GetTrackingByLatitudeAndLongitudeUseCase getTrackingByLatitudeAndLongitudeUseCase;
    private final TrackingConverter trackingConverter;

    @Override
    @PutMapping("/{trackingId}/location")
    public ResponseEntity<Void> updateTrackingLatitudeAndLongitude(
            @PathVariable Long trackingId,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        updateTrackingLatitudeAndLongitudeUseCase.update(trackingId, latitude, longitude);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TrackingDTO>> getTrackingByLatitudeAndLongitude(
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        List<Tracking> trackingList = getTrackingByLatitudeAndLongitudeUseCase.getTrackingByLatitudeAndLongitude(latitude, longitude);
        return ResponseEntity.ok(trackingConverter.convertToDTOList(trackingList));
    }
}
