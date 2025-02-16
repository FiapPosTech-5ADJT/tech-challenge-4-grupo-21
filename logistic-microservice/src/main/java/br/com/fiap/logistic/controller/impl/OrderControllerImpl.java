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
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderControllerImpl implements OrderController {

    private final GetOrdersByZipCodeUseCase getOrdersByZipCodeUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;
    private final OrderConverter orderConverter;

    @Override
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrdersByZipCode(@RequestParam String zipCode) {
        if (zipCode == null || !Pattern.matches("\\d{5}-?\\d{3}", zipCode)) {
            return ResponseEntity.badRequest().build();
        }
        List<Order> orders = getOrdersByZipCodeUseCase.getOrdersByZipCode(zipCode);
        return ResponseEntity.ok(orders.stream().map(orderConverter::convertToDTO).toList());
    }

    @Override
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        if (Arrays.stream(OrderStatus.values()).map(Enum::name).noneMatch(status::equals)) {
            return ResponseEntity.badRequest().build();
        }
        updateOrderStatusUseCase.updateOrderStatus(orderId, OrderStatus.valueOf(status));
        return ResponseEntity.ok().build();
    }
}
