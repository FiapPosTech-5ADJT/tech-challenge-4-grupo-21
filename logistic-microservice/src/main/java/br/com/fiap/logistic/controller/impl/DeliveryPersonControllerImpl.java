package br.com.fiap.logistic.controller.impl;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.controller.DeliveryPersonController;
import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.dto.*;
import br.com.fiap.logistic.usecase.AssignDeliveryPersonUseCase;
import br.com.fiap.logistic.usecase.CompleteDeliveryUseCase;
import br.com.fiap.logistic.usecase.CreateDeliveryPersonUseCase;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery-person")
public class DeliveryPersonControllerImpl implements DeliveryPersonController {

    private final CreateDeliveryPersonUseCase createDeliveryPersonUseCase;
    private final AssignDeliveryPersonUseCase assignDeliveryPersonUseCase;
    private final CompleteDeliveryUseCase completeDeliveryUseCase;
    private final DeliveryPersonConverter deliveryPersonConverter;

    @Override
    public ResponseEntity<DeliveryPersonResponseDTO> create(@RequestBody DeliveryPersonDTO deliveryPersonDTO) {
        DeliveryPerson deliveryPerson = deliveryPersonConverter.convertToDomain(deliveryPersonDTO);
        DeliveryPersonResponseDTO createdDeliveryPersonResponseDTO = deliveryPersonConverter.convertToResponseDTO(createDeliveryPersonUseCase.create(deliveryPerson));
        return ResponseEntity.ok(createdDeliveryPersonResponseDTO);
    }

    @Override
    public ResponseEntity<AssignDeliveryResponseDTO> assignDeliveryPerson(@RequestBody AssignDeliveryDto assignDeliveryDto) {
        Tracking tracking = assignDeliveryPersonUseCase.assign(
                assignDeliveryDto.deliveryPersonId(),
                assignDeliveryDto.orderId(),
                assignDeliveryDto.zipCode()
        );
        AssignDeliveryResponseDTO assignDeliveryResponseDTO = new AssignDeliveryResponseDTO(tracking.getId());
        return ResponseEntity.ok(assignDeliveryResponseDTO);
    }

    @Override
    public ResponseEntity<Void> completeDelivery(@PathVariable Long orderId) {
        completeDeliveryUseCase.complete(orderId);
        return ResponseEntity.noContent().build();
    }

  @Bean
  public Consumer<Message<LogisticOrderDTO>> orderToDelivery() {
    return message -> {
      LogisticOrderDTO request = message.getPayload();
      assignDeliveryPersonUseCase.assign(
        1L,
         request.getOrderId(),
        "64058120"

      );
    };
  }
}
