package br.com.fiap.logistic.usecase;

import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.service.OrderService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetOrdersByZipCodeUseCase {
    private final OrderService orderService;

    public List<Order> getOrdersByZipCode(String zipCode) {
        return orderService.getOrdersByZipCode(zipCode);
    }

}
