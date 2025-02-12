package br.com.fiap.logistic.usecase;

import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.service.DeliveryPersonService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateDeliveryPersonUseCase {

    private final DeliveryPersonService deliveryPersonService;

    public DeliveryPerson create(DeliveryPerson deliveryPerson) {
        return deliveryPersonService.createDeliveryPerson(deliveryPerson);
    }
}
