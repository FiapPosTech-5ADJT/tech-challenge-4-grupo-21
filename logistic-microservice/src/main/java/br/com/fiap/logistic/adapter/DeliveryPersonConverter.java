package br.com.fiap.logistic.adapter;

import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.dto.DeliveryPersonDTO;
import br.com.fiap.logistic.dto.DeliveryPersonResponseDTO;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeliveryPersonConverter {

    public DeliveryPersonEntity convertToEntity(DeliveryPerson deliveryPerson) {
        return DeliveryPersonEntity.builder()
                .id(deliveryPerson.getId())
                .name(deliveryPerson.getName())
                .vehiclePlate(deliveryPerson.getVehiclePlate())
                .available(deliveryPerson.isAvailable())
                .build();
    }

    public DeliveryPerson convertToDomain(DeliveryPersonEntity deliveryPersonEntity) {
        return new DeliveryPerson(deliveryPersonEntity.getId(),
                deliveryPersonEntity.getName(),
                deliveryPersonEntity.getVehiclePlate(),
                deliveryPersonEntity.isAvailable());
    }

    public DeliveryPerson convertToDomain(DeliveryPersonDTO deliveryPersonDTO) {
        return new DeliveryPerson(deliveryPersonDTO.name(), deliveryPersonDTO.vehiclePlate());
    }

    public DeliveryPersonDTO convertToDTO(DeliveryPerson deliveryPerson) {
        return new DeliveryPersonDTO(deliveryPerson.getName(), deliveryPerson.getVehiclePlate());
    }

    public DeliveryPersonResponseDTO convertToResponseDTO(DeliveryPerson deliveryPersonEntity) {
        return new DeliveryPersonResponseDTO(deliveryPersonEntity.getId(),
                deliveryPersonEntity.getName(),
                deliveryPersonEntity.getVehiclePlate());
    }
}
