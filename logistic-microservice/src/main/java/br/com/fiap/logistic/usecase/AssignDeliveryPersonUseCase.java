package br.com.fiap.logistic.usecase;

import br.com.fiap.logistic.service.DeliveryPersonService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignDeliveryPersonUseCase {

    private final DeliveryPersonService deliveryPersonService;

    public void assign(Long deliveryPersonId, Long orderId) {
        deliveryPersonService.assignOrderToDeliveryPerson(deliveryPersonId, orderId);
    }
}
