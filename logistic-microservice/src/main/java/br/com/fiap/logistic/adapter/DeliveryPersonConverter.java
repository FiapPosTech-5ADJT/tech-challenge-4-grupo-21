package br.com.fiap.logistic.adapter;

import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.dto.DeliveryPersonDTO;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@AllArgsConstructor
public class DeliveryPersonConverter {

    final OrderConverter orderConverter;

    public DeliveryPersonEntity convertToEntity(DeliveryPerson deliveryPerson) {
        return DeliveryPersonEntity.builder()
                .id(deliveryPerson.getId())
                .name(deliveryPerson.getName())
                .available(deliveryPerson.isAvailable())
                .orders(deliveryPerson.getOrders().stream().map(orderConverter::convertToEntity).collect(Collectors.toList()))
                .build();
    }

    public DeliveryPerson convertToDomain(DeliveryPersonEntity deliveryPersonEntity) {
        return new DeliveryPerson(deliveryPersonEntity.getId(),
                deliveryPersonEntity.getName(),
                deliveryPersonEntity.getVehiclePlate(),
                deliveryPersonEntity.isAvailable(),
                deliveryPersonEntity.getOrders().stream().map(orderConverter::convertToDomain).collect(Collectors.toList()));
    }

    public DeliveryPerson convertToDomain(DeliveryPersonDTO deliveryPersonDTO) {
        return new DeliveryPerson(deliveryPersonDTO.name(), deliveryPersonDTO.vehiclePlate());
    }

    public DeliveryPersonDTO convertToDTO(DeliveryPerson deliveryPerson) {
        return new DeliveryPersonDTO(deliveryPerson.getName(), deliveryPerson.getVehiclePlate());
    }
}
