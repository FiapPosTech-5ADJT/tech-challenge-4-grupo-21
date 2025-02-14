package br.com.fiap.logistic.service.impl;

import br.com.fiap.logistic.adapter.OrderConverter;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.OrderStatus;
import br.com.fiap.logistic.entity.OrderEntity;
import br.com.fiap.logistic.gateway.OrderGateway;
import br.com.fiap.logistic.service.OrderService;
import lombok.AllArgsConstructor;

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
        return orderGateway.getOrderById(id);
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        final Order order = getOrderById(id);
        order.setStatus(status);
        orderGateway.save(orderConverter.convertToEntity(order));
    }

    @Override
    public List<Order> getOrdersByZipCode(String zipCode) {
        return orderGateway.getOrdersByZipCode(zipCode).stream().map(orderConverter::convertToDomain).toList();
    }
}
