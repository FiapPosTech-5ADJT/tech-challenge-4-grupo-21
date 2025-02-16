package br.com.fiap.logistic.service;

import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order createOrder(Order order);

    Optional<Order> getOrderById(Long id);

    void updateOrderStatus(Long id, OrderStatus status);

    List<Order> getOrdersByZipCode(String zipCode);

    List<Order> getOrdersByDeliveryPerson(Long deliveryPersonId);
}
