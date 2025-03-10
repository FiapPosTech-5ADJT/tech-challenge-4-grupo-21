package br.com.fiap.logistic.service.impl;

import br.com.fiap.logistic.adapter.OrderConverter;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.OrderStatus;
import br.com.fiap.logistic.entity.OrderEntity;
import br.com.fiap.logistic.gateway.OrderGateway;
import br.com.fiap.logistic.service.OrderService;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderGateway orderGateway;
    private final OrderConverter orderConverter;

    @Override
    public Order createOrder(Order order) {
        final OrderEntity orderEntity = orderConverter.convertToEntity(order);
        return orderConverter.convertToDomain(orderGateway.save(orderEntity));
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderGateway.getOrderByExternalId(id)
                .map(orderConverter::convertToDomain);
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        final Order order = getOrderById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order.setDeliveredAt(LocalDateTime.now());
        orderGateway.save(orderConverter.convertToEntity(order));
    }

    @Override
    public List<Order> getOrdersByZipCode(String zipCode) {
        return orderGateway.getOrdersByZipCode(zipCode).stream().map(orderConverter::convertToDomain).toList();
    }

    @Override
    public List<Order> getOrdersByDeliveryPerson(Long deliveryPersonId) {
        return orderGateway.getOrdersByDeliveryPerson(deliveryPersonId).stream().map(orderConverter::convertToDomain).toList();
    }
}
