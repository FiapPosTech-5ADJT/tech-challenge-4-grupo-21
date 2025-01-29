package br.com.fiap.productcatalog.infraestructure.gateway;

import br.com.fiap.productcatalog.domain.entity.Product;
import br.com.fiap.productcatalog.domain.gateway.ProductGateway;
import br.com.fiap.productcatalog.infraestructure.persistence.converter.db.ProductEntityConverter;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.repository.ProductRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

    private final ProductRepository productRepository;
    private final ProductEntityConverter productEntityConverter;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).map(productEntityConverter::toDomainObj)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    @Override
    public BigDecimal getProductStock(Long id) {
        Product product = findById(id);
        return product.getStock();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().map(productEntityConverter::toDomainObj).toList();
    }

    @Override
    public void addStock(Product product) {
        Product productToUpdate = findById(product.getId());
        productRepository.save(productEntityConverter.toEntity(productToUpdate));
    }

    @Override
    public void removeStock(Product product) {
        Product productToUpdate = findById(product.getId());
        productRepository.save(productEntityConverter.toEntity(productToUpdate));
    }
}
