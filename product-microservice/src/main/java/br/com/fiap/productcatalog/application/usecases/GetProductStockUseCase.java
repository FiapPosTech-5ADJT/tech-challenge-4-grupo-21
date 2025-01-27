package br.com.fiap.productcatalog.application.usecases;

import br.com.fiap.productcatalog.domain.service.ProductService;

import java.math.BigDecimal;

public class GetProductStockUseCase {

    private final ProductService productService;

    public GetProductStockUseCase(ProductService productService) {
        this.productService = productService;
    }

    public BigDecimal getProductStock(Long productId) {
        return productService.getProductStock(productId);
    }
}
