package br.com.fiap.logistic.controller;

import br.com.fiap.logistic.dto.TrackingDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Rastreamento [TrackingController]",
        description = "Controlador responsável pelo rastreamento de entregas."
)
public interface TrackingController {

    @Operation(summary = "Atualizar latitude e longitude do rastreamento.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Localização atualizada com sucesso."
            )
    })
    ResponseEntity<Void> updateTrackingLatitudeAndLongitude(
            @Parameter(description = "ID do do pedido.") Long orderId,
            @Parameter(description = "Nova latitude.") Double latitude,
            @Parameter(description = "Nova longitude.") Double longitude
    );

    @Operation(summary = "Buscar rastreamento por latitude e longitude.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Rastreamento encontrado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TrackingDTO.class))
                    })
    })
    ResponseEntity<List<TrackingDTO>> getTrackingByLatitudeAndLongitude(
            @Parameter(description = "Latitude.") Double latitude,
            @Parameter(description = "Longitude.") Double longitude
    );
}
