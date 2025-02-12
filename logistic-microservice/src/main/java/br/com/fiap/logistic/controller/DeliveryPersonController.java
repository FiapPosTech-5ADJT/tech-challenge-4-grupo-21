package br.com.fiap.logistic.controller;

import br.com.fiap.logistic.dto.DeliveryPersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Entregadores [DeliveryPersonController]",
        description = "Controlador que fornece os serviços de criação, atribuição e conclusão de entregas."
)
public interface DeliveryPersonController {

    @Operation(summary = "Criar um entregador.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Entregador criado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DeliveryPersonDTO.class))
                    })
    })
    ResponseEntity<DeliveryPersonDTO> create(
            @Parameter(description = "Dados do entregador a ser criado.") DeliveryPersonDTO deliveryPersonDTO
    );

    @Operation(summary = "Atribuir um entregador a uma entrega.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entregador atribuído com sucesso."
            )
    })
    ResponseEntity<Void> assignDeliveryPerson(
            @Parameter(description = "ID do entregador.") Long deliveryPersonId,
            @Parameter(description = "ID da entrega.") Long orderId
    );

    @Operation(summary = "Concluir uma entrega.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entrega concluída com sucesso."
            )
    })
    ResponseEntity<Void> completeDelivery(
            @Parameter(description = "ID da entrega a ser concluída.") Long orderId
    );
}
