package br.com.fiap.logistic.controller.impl;

import br.com.fiap.logistic.controller.OrderController;
import br.com.fiap.logistic.dto.OrderDTO;
import br.com.fiap.logistic.usecase.GetOrdersByZipCodeUseCase;
import br.com.fiap.logistic.usecase.UpdateOrderStatusUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@AllArgsConstructor
@RestController
public class OrderControllerImpl implements OrderController {

    private final GetOrdersByZipCodeUseCase getOrdersByZipCodeUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    @Override
    public ResponseEntity<List<OrderDTO>> getOrdersByZipCode(String zipCode) {
        List<OrderDTO> orders = getOrdersByZipCodeUseCase.getOrdersByZipCode(zipCode);
        return ResponseEntity.ok(orders);
    }

    @Override
    public ResponseEntity<Void> updateOrderStatus(Long orderId, String status) {
        updateOrderStatusUseCase.updateStatus(orderId, status);
        return ResponseEntity.ok().build();
    }
}
