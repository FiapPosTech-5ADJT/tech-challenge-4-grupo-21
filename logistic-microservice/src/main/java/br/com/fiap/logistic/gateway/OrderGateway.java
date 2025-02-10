package br.com.fiap.logistic.gateway;

import br.com.fiap.logistic.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderGateway {

    OrderEntity save(OrderEntity order);

    Optional<OrderEntity> getOrderById(Long id);

    List<OrderEntity> getOrdersByZipCode(String zipCode);
}
