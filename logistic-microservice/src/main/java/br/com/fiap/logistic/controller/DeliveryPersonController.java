package br.com.fiap.logistic.controller;

import br.com.fiap.logistic.dto.AssignDeliveryDto;
import br.com.fiap.logistic.dto.DeliveryPersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Entregadores [DeliveryPersonController]",
        description = "Controlador que fornece os serviços de criação, atribuição e conclusão de entregas."
)
@RequestMapping("/delivery-person")
public interface DeliveryPersonController {

    @Operation(summary = "Criar um entregador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entregador criado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeliveryPersonDTO.class)))
    })
    @PostMapping
    ResponseEntity<DeliveryPersonDTO> create(
            @Parameter(description = "Dados do entregador a ser criado.") @RequestBody DeliveryPersonDTO deliveryPersonDTO
    );

    @Operation(summary = "Atribuir um entregador a uma entrega.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entregador atribuído com sucesso.")
    })
    @PutMapping("/assign")
    ResponseEntity<Void> assignDeliveryPerson(
            @Parameter(description = "Dados para atribuição do entregador.") @RequestBody AssignDeliveryDto assignDeliveryDto
    );

    @Operation(summary = "Concluir uma entrega.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entrega concluída com sucesso.")
    })
    @PutMapping("/complete/{orderId}")
    ResponseEntity<Void> completeDelivery(
            @Parameter(description = "ID da entrega a ser concluída.") @PathVariable Long orderId
    );
}
