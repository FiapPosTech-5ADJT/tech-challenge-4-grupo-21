package br.com.fiap.logistic.usecase;

import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.service.OrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOrderUseCase {
    private final OrderService orderService;

    public void createOrder(Order order) {
        orderService.createOrder(order);
    }
}
