package br.com.fiap.logistic.adapter;

import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.entity.OrderEntity;

public class OrderConverter {

    public Order convertToDomain(OrderEntity orderEntity) {
        return new Order(orderEntity.getId(),orderEntity.getCustomerId(),orderEntity.getStatus(),orderEntity.getCreatedAt(),orderEntity.getEstimatedDelivery(),orderEntity.getDeliveredAt(),orderEntity.getZipCode());
    }

    public OrderEntity convertToEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .estimatedDelivery(order.getEstimatedDelivery())
                .deliveredAt(order.getDeliveredAt())
                .zipCode(order.getZipCode())
                .build();
    }
}
