package br.com.fiap.logistic.gateway.impl;

import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import br.com.fiap.logistic.gateway.DeliveryPersonGateway;
import br.com.fiap.logistic.repository.DeliveryPersonRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DeliveryPersonGatewayImpl implements DeliveryPersonGateway {

    private final DeliveryPersonRepository deliveryPersonRepository;

    @Override
    public DeliveryPersonEntity save(DeliveryPersonEntity deliveryPerson) {
        return deliveryPersonRepository.save(deliveryPerson);
    }

    @Override
    public Optional<DeliveryPersonEntity> findById(Long deliveryPersonId) {
        return deliveryPersonRepository.findById(deliveryPersonId);
    }

    @Override
    public Optional<DeliveryPersonEntity> findByOrderId(Long orderId) {
        return deliveryPersonRepository.findByOrderId(orderId);
    }
}
