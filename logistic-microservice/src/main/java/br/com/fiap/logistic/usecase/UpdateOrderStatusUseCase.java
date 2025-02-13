package br.com.fiap.logistic.usecase;

import br.com.fiap.logistic.service.OrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateOrderStatusUseCase {

    private final OrderService orderService;

    public void updateOrderStatus(Long orderId, String status) {
        orderService.updateOrderStatus(orderId, status);
    }
}
