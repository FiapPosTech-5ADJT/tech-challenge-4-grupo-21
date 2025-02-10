package br.com.fiap.logistic.adapter;

import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;

import java.util.stream.Collectors;

public class DeliveryPersonConverter {

    private DeliveryPersonConverter() {
    }

    public DeliveryPersonEntity convertToEntity(DeliveryPerson deliveryPerson) {
        return DeliveryPersonEntity.builder()
                .id(deliveryPerson.getId())
                .name(deliveryPerson.getName())
                .available(deliveryPerson.isAvailable())
                .orders(deliveryPerson.getOrders().stream().map(OrderConverter::convertToEntity).collect(Collectors.toList()))
                .build();
    }

    public DeliveryPerson convertToDomain(DeliveryPersonEntity deliveryPersonEntity) {
        return new DeliveryPerson(deliveryPersonEntity.getId(),
                deliveryPersonEntity.getName(),
                deliveryPersonEntity.getVehiclePlate(),
                deliveryPersonEntity.isAvailable(),
                deliveryPersonEntity.getOrders().stream().map(OrderConverter::convertToDomain).collect(Collectors.toList()));
    }
}
