package br.com.fiap.productcatalog.domain.service;

import br.com.fiap.productcatalog.domain.entity.Product;
import br.com.fiap.productcatalog.domain.gateway.ProductGateway;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {

    private final ProductGateway productGateway;

    public ProductService(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public void addStock(Long productId, BigDecimal quantity) {
        Product product = getProductById(productId);
        product.addStock(quantity);
        productGateway.addStock(product);
    }

    public void removeStock(Long productId, BigDecimal quantity) {
        Product product = getProductById(productId);
        product.removeStock(quantity);
        productGateway.removeStock(product);
    }

    public Product getProductById(Long id) {
        return productGateway.findById(id);
    }

    public BigDecimal getProductStock(Long id) {
        return productGateway.getProductStock(id);
    }

    public List<Product> getProducts() {
        return productGateway.findAll();
    }
}