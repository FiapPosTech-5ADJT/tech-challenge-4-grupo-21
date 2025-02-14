package br.com.fiap.logistic.service;

import br.com.fiap.logistic.domain.DeliveryPerson;

public interface DeliveryPersonService {

    DeliveryPerson createDeliveryPerson(DeliveryPerson deliveryPerson);

    void assignOrderToDeliveryPerson(Long deliveryPersonId, Long orderId);

    void completeDelivery(Long orderId);
}
