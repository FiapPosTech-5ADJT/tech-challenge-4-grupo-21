package br.com.fiap.logistic.controller.impl;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.controller.DeliveryPersonController;
import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.dto.DeliveryPersonDTO;
import br.com.fiap.logistic.usecase.AssignDeliveryPersonUseCase;
import br.com.fiap.logistic.usecase.CompleteDeliveryUseCase;
import br.com.fiap.logistic.usecase.CreateDeliveryPersonUseCase;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class DeliveryPersonControllerImpl implements DeliveryPersonController {

    private final CreateDeliveryPersonUseCase createDeliveryPersonUseCase;
    private final AssignDeliveryPersonUseCase assignDeliveryPersonUseCase;
    private final CompleteDeliveryUseCase completeDeliveryUseCase;
    private final DeliveryPersonConverter deliveryPersonConverter;

    @Override
    public ResponseEntity<DeliveryPersonDTO> create(DeliveryPersonDTO deliveryPersonDTO) {
        DeliveryPerson deliveryPerson = deliveryPersonConverter.convertToDomain(deliveryPersonDTO);
        DeliveryPersonDTO createdDeliveryPersonDTO = deliveryPersonConverter.convertToDTO(createDeliveryPersonUseCase.create(deliveryPerson));
        return ResponseEntity.ok(createdDeliveryPersonDTO);
    }

    @Override
    public ResponseEntity<Void> assignDeliveryPerson(Long deliveryPersonId, Long orderId) {
        assignDeliveryPersonUseCase.assign(deliveryPersonId, orderId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> completeDelivery(Long orderId) {
        completeDeliveryUseCase.complete(orderId);
        return ResponseEntity.noContent().build();
    }
}
