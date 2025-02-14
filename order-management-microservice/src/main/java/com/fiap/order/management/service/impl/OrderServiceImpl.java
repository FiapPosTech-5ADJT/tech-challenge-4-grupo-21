package com.fiap.order.management.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.order.management.adapter.mapper.OrderMapper;
import com.fiap.order.management.controller.exception.EnviarMensagemException;
import com.fiap.order.management.controller.exception.NotFoundException;
import com.fiap.order.management.controller.exception.StockUpdateException;
import com.fiap.order.management.domain.ItemDomain;
import com.fiap.order.management.domain.OrderDomain;
import com.fiap.order.management.dto.*;
import com.fiap.order.management.gateway.*;
import com.fiap.order.management.entity.Order;
import com.fiap.order.management.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderGateway orderGateway;
    private final CustomerGateway customerGateway;
    private final ProductGateway productGateway;
    private final StockGateway stockGateway;
    private final LogisticGateway logisticGateway;
    private final ObjectMapper objectMapper;

    @Transactional
    public OrderDomain create(OrderDomain orderDomain) {
        Order orderEntity = OrderMapper.toModel(orderDomain);
        orderDomain.validate();

        try {
            validateCustomer(orderDomain.getCustomerId());
            validateEnoughStock(orderDomain.getItems());
            manageInStockProducts(orderDomain.getItems());

            Order savedOrder = this.orderGateway.save(orderEntity);

            // Notify logistics service
            LogisticOrderDTO logisticOrderDTO = OrderMapper.toLogisticOrderDTO(savedOrder);
            String json = objectMapper.writeValueAsString(logisticOrderDTO);
            logisticGateway.sendOrderToDelivery(json);

            return  OrderMapper.toDomain(savedOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Não foi possível cadastrar o pedido.");
        }
    }

  private void validateEnoughStock(List<ItemDomain> products) {
    products.forEach(product -> {
      ResponseEntity<BigDecimal> responseEntity = productGateway.findById(product.getProductId());
      BigDecimal productQuantity = responseEntity.getBody();

      if (productQuantity == null || productQuantity.compareTo(product.getQuantity()) < 0) {
        throw new UnsupportedOperationException("Sem disponibilidade do produto em estoque");
      }
    });
  }

    private void manageInStockProducts(List<ItemDomain> products) {
        products.forEach(product -> {
            StockProductUpdateRequestDTO stockProductUpdateRequestDTO = new StockProductUpdateRequestDTO(
                    product.getProductId(),
                    product.getQuantity()
            );
          try {
            String json = objectMapper.writeValueAsString(stockProductUpdateRequestDTO);
            this.stockGateway.updateStock(json);
          } catch (JsonProcessingException e) {
            throw new StockUpdateException("Error processing JSON for stock update", e);
          } catch (Exception e) {
            throw new EnviarMensagemException();
          }
        });

    }

    private void validateCustomer(Long customerId) {
        if (Objects.isNull(customerGateway.findById(customerId))) {
            throw new NotFoundException(String.format("Customer with ID '%s' not found.", customerId));
        }
    }

    public OrderDomain findById(Long id) {
        validateExistence(id);
        Order order = this.orderGateway.findById(id).orElseThrow(() -> createNotFoundOrderException(id));
        return OrderMapper.toDomain(order);
    }

    private void validateExistence(Long id) {
        if (!this.orderGateway.existsById(id)) throw createNotFoundOrderException(id);
    }

    private NotFoundException createNotFoundOrderException(Long id) {
        return new NotFoundException(String.format("Order with ID '%s' not found.", id));
    }
}
