package br.com.fiap.logistic.usecase;

import br.com.fiap.logistic.domain.OrderStatus;
import br.com.fiap.logistic.service.OrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateOrderStatusUseCase {

    private final OrderService orderService;

    public void updateOrderStatus(Long orderId, OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
    }
}
