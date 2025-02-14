package com.fiap.order.management.gateway.api;

import com.fiap.order.management.clients.ProductClient;
import com.fiap.order.management.gateway.ProductGateway;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public class ProductGatewayImpl implements ProductGateway {
    private final ProductClient productClient;

    public ProductGatewayImpl(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public ResponseEntity<BigDecimal> findById(Long productId) {
        return productClient.findById(productId);
    }
}
