package br.com.fiap.logistic.service.impl;

import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.OrderStatus;
import br.com.fiap.logistic.gateway.OrderGateway;
import br.com.fiap.logistic.service.OrderService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderGateway orderGateway;

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {

    }

    @Override
    public List<Order> getOrdersByZipCode(String zipCode) {
        return List.of();
    }
}
