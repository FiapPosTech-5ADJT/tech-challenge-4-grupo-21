package br.com.fiap.logistic.controller.impl;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.controller.DeliveryPersonController;
import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.dto.AssignDeliveryDto;
import br.com.fiap.logistic.dto.DeliveryPersonDTO;
import br.com.fiap.logistic.usecase.AssignDeliveryPersonUseCase;
import br.com.fiap.logistic.usecase.CompleteDeliveryUseCase;
import br.com.fiap.logistic.usecase.CreateDeliveryPersonUseCase;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery-person")
public class DeliveryPersonControllerImpl implements DeliveryPersonController {

    private final CreateDeliveryPersonUseCase createDeliveryPersonUseCase;
    private final AssignDeliveryPersonUseCase assignDeliveryPersonUseCase;
    private final CompleteDeliveryUseCase completeDeliveryUseCase;
    private final DeliveryPersonConverter deliveryPersonConverter;

    @Override
    public ResponseEntity<DeliveryPersonDTO> create(@RequestBody DeliveryPersonDTO deliveryPersonDTO) {
        DeliveryPerson deliveryPerson = deliveryPersonConverter.convertToDomain(deliveryPersonDTO);
        DeliveryPersonDTO createdDeliveryPersonDTO = deliveryPersonConverter.convertToDTO(createDeliveryPersonUseCase.create(deliveryPerson));
        return ResponseEntity.ok(createdDeliveryPersonDTO);
    }

    @Override
    public ResponseEntity<Void> assignDeliveryPerson(@RequestBody AssignDeliveryDto assignDeliveryDto) {
        assignDeliveryPersonUseCase.assign(
                assignDeliveryDto.deliveryPersonId(),
                assignDeliveryDto.orderId(),
                assignDeliveryDto.zipCode()
        );
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> completeDelivery(@PathVariable Long orderId) {
        completeDeliveryUseCase.complete(orderId);
        return ResponseEntity.noContent().build();
    }

}
