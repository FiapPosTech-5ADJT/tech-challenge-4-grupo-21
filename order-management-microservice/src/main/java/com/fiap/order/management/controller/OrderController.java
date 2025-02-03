package com.fiap.order.management.controller;

import com.fiap.order.management.dto.OrderDTO;
import com.fiap.order.management.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Pedidos [OrderController]",
        description = "Controlador que fornece os serviços de criação e consulta de pedidos."
)
public interface OrderController {
    @Operation(summary = "Criar um pedido.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Pedido criado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))
                    })
    })
    ResponseEntity<OrderDTO> create(
      @Parameter(description = "Descrição do pedido a ser criado.")OrderDTO orderDTO
      );

    @Operation(summary = "Buscar um pedido pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedido encontrado com sucesso.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))
                    })
    })
    ResponseEntity<OrderDTO> findById(@Parameter(description = "Id do pedido a ser consultado.") Long orderId);

}
