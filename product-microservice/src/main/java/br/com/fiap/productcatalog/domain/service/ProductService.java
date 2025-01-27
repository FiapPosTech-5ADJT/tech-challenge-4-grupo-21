package br.com.fiap.productcatalog.domain.service;

import br.com.fiap.productcatalog.domain.entity.Product;
import br.com.fiap.productcatalog.domain.gateway.ProductGateway;
import br.com.fiap.productcatalog.domain.gateway.CategoryGateway;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {

    private final ProductGateway productGateway;
    private final CategoryGateway categoryGateway;

    public ProductService(ProductGateway productGateway, CategoryGateway categoryGateway) {
        this.productGateway = productGateway;
        this.categoryGateway = categoryGateway;
    }

    public Product createProduct(Product product) {
        validateProduct(product);
        return productGateway.save(product);
    }

    public void addStock(Long productId, BigDecimal quantity) {
        Product product = getProductById(productId);
        product.addStock(quantity);
        productGateway.save(product);
    }

    public void removeStock(Long productId, BigDecimal quantity) {
        Product product = getProductById(productId);
        product.removeStock(quantity);
        productGateway.save(product);
    }

    public Product getProductById(Long id) {
        return productGateway.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public Product findProductByName(String name) {
        return productGateway.findByName(name).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public void deleteProduct(Long id) {
        productGateway.deleteById(id);
    }

    public BigDecimal getProductStock(Long id) {
        return productGateway.getProductStock(id);
    }

    public List<Product> getProducts() {
        return productGateway.findAll();
    }

    private void validateProduct(Product product) {
        if (product.getCategory() == null || categoryGateway.findById(product.getCategory().getId()).isEmpty()) {
            throw new IllegalArgumentException("Invalid category");
        }
    }
}