package br.com.fiap.logistic.usecase;

import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.service.DeliveryPersonService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignDeliveryPersonUseCase {

    private final DeliveryPersonService deliveryPersonService;

    public Tracking assign(Long deliveryPersonId, Long orderId, String zipCode) {
        return deliveryPersonService.assignOrderToDeliveryPerson(deliveryPersonId, orderId,zipCode);
    }
}
