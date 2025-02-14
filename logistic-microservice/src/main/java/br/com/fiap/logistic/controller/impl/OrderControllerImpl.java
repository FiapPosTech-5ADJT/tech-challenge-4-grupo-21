package br.com.fiap.logistic.controller.impl;

import br.com.fiap.logistic.adapter.OrderConverter;
import br.com.fiap.logistic.controller.OrderController;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.OrderStatus;
import br.com.fiap.logistic.dto.OrderDTO;
import br.com.fiap.logistic.usecase.GetOrdersByZipCodeUseCase;
import br.com.fiap.logistic.usecase.UpdateOrderStatusUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
@RestController
public class OrderControllerImpl implements OrderController {

    private final GetOrdersByZipCodeUseCase getOrdersByZipCodeUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;
    private final OrderConverter orderConverter;

    @Override
    public ResponseEntity<List<OrderDTO>> getOrdersByZipCode(String zipCode) {
        if (zipCode == null || !Pattern.matches("\\d{5}-?\\d{3}", zipCode)) {
            return ResponseEntity.badRequest().build();
        }
        List<Order> orders = getOrdersByZipCodeUseCase.getOrdersByZipCode(zipCode);
        return ResponseEntity.ok(orders.stream().map(orderConverter::convertToDTO).toList());
    }

    @Override
    public ResponseEntity<Void> updateOrderStatus(Long orderId, String status) {
        updateOrderStatusUseCase.updateOrderStatus(orderId, OrderStatus.valueOf(status));
        return ResponseEntity.ok().build();
    }
}
