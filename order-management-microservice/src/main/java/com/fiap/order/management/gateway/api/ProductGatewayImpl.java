package com.fiap.order.management.gateway.api;

import com.fiap.order.management.clients.ProductClient;
import com.fiap.order.management.dto.ProductFindByIdResponseDTO;
import com.fiap.order.management.gateway.ProductGateway;

public class ProductGatewayImpl implements ProductGateway {
    private final ProductClient productClient;

    public ProductGatewayImpl(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public ProductFindByIdResponseDTO findById(String productId) {
        return productClient.findById(productId);
    }
}
