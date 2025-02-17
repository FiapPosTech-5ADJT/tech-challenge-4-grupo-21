package br.com.fiap.logistic.service;

import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.domain.Tracking;

public interface DeliveryPersonService {

    DeliveryPerson createDeliveryPerson(DeliveryPerson deliveryPerson);

    Tracking assignOrderToDeliveryPerson(Long deliveryPersonId, Long orderId, String zipCode);

    void completeDelivery(Long orderId);
}
