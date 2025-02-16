package br.com.fiap.logistic.adapter;

import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.dto.OrderDTO;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import br.com.fiap.logistic.entity.OrderEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderConverter {

    public Order convertToDomain(OrderEntity orderEntity) {
        return new Order(orderEntity.getId(),
                orderEntity.getExternalId(),
                orderEntity.getStatus(),
                orderEntity.getCreatedAt(),
                orderEntity.getEstimatedDelivery(),
                orderEntity.getDeliveredAt(),
                orderEntity.getZipCode(),
                orderEntity.getDeliveryPerson() != null
                        ? orderEntity.getDeliveryPerson().getId()
                        : null);
    }

    public OrderEntity convertToEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .externalId(order.getOrderExternalId())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .estimatedDelivery(order.getEstimatedDelivery())
                .deliveredAt(order.getDeliveredAt())
                .zipCode(order.getZipCode())
                .deliveryPerson(order.getDeliveryPersonId() != null
                        ? DeliveryPersonEntity.builder().id(order.getDeliveryPersonId()).build()
                        : null)
                .build();
    }

    public OrderDTO convertToDTO(Order order) {
        return new OrderDTO(order.getOrderExternalId(),
                order.getEstimatedDelivery(),
                order.getDeliveredAt(),
                order.getZipCode(),
                order.getStatus().name());
    }
}
