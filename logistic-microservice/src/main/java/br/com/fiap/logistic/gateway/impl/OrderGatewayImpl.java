package br.com.fiap.logistic.gateway.impl;

import br.com.fiap.logistic.entity.OrderEntity;
import br.com.fiap.logistic.gateway.OrderGateway;
import br.com.fiap.logistic.repository.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

    private final OrderRepository orderRepository;

    @Override
    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<OrderEntity> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<OrderEntity> getOrdersByZipCode(String zipCode) {
        return orderRepository.getReferenceByZipCode(zipCode);
    }
}
