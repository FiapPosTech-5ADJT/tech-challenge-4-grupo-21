package br.com.fiap.productcatalog.application.usecases;

import br.com.fiap.productcatalog.domain.service.ProductService;

import java.math.BigDecimal;

public class AddProductStockUseCase {

    private final ProductService productService;

    public AddProductStockUseCase(ProductService productService) {
        this.productService = productService;
    }

    public void addProductStock(Long productId, BigDecimal quantity) {
        productService.addStock(productId, quantity);
    }
}
