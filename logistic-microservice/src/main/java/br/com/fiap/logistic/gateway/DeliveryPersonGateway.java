package br.com.fiap.logistic.gateway;

import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;

import java.util.Optional;

public interface DeliveryPersonGateway {

    DeliveryPersonEntity save(DeliveryPersonEntity deliveryPerson);

    Optional<DeliveryPersonEntity> findById(Long deliveryPersonId);

    Optional<DeliveryPersonEntity> findByOrderId(Long orderId);
}
