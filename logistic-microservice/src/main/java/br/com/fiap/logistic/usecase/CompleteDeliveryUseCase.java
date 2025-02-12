package br.com.fiap.logistic.usecase;

import br.com.fiap.logistic.service.DeliveryPersonService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompleteDeliveryUseCase {

    private final DeliveryPersonService deliveryService;

    public void complete(Long orderId) {
        deliveryService.completeDelivery(orderId);
    }
}
