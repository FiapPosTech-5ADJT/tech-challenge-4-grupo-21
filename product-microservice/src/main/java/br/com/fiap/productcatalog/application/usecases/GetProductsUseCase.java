package br.com.fiap.productcatalog.application.usecases;

import br.com.fiap.productcatalog.domain.entity.Product;
import br.com.fiap.productcatalog.domain.service.ProductService;

import java.util.List;

public class GetProductsUseCase {

    private final ProductService productService;

    public GetProductsUseCase(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> getProducts() {
        return productService.getProducts();
    }

}
