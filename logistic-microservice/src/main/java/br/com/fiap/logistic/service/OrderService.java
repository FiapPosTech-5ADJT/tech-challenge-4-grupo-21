package br.com.fiap.logistic.service;

import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.OrderStatus;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order getOrderById(Long id);

    void updateOrderStatus(Long id, OrderStatus status);

    List<Order> getOrdersByZipCode(String zipCode);
}
