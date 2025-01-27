package br.com.fiap.productcatalog.application.usecases;

import br.com.fiap.productcatalog.domain.service.ProductService;

import java.math.BigDecimal;

public class RemoveProductStockUseCase {

    private final ProductService productService;

    public RemoveProductStockUseCase(ProductService productService) {
        this.productService = productService;
    }

    public void removeProductStock(Long productId, BigDecimal quantity) {
        productService.removeStock(productId, quantity);
    }
}
