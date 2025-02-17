package br.com.fiap.logistic.controller;

import br.com.fiap.logistic.dto.OrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Pedidos [OrderController]",
        description = "Controlador responsável pela consulta e atualização de pedidos."
)
@RequestMapping("/orders")
public interface OrderController {

    @Operation(summary = "Buscar pedidos por CEP.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedidos encontrados com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
                    })
    })
    @GetMapping
    ResponseEntity<List<OrderDTO>> getOrdersByZipCode(
            @Parameter(description = "CEP para busca de pedidos.") @RequestParam String zipCode
    );

    @Operation(summary = "Atualizar status de um pedido.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Status do pedido atualizado com sucesso."
            )
    })
    @PutMapping("/{orderId}/status")
    ResponseEntity<Void> updateOrderStatus(
            @Parameter(description = "ID do pedido.") @PathVariable Long orderId,
            @Parameter(description = "Novo status do pedido. As opções são: PENDING, IN_TRANSIT, COMPLETED, CANCELED.") @RequestParam String status
    );
}
