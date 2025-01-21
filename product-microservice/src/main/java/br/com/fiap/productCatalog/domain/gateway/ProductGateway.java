package br.com.fiap.productCatalog.domain.gateway;

import br.com.fiap.productCatalog.domain.entity.Product;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductGateway {

    Product save(Product product);

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    void deleteById(Long id);

    BigDecimal getProductStock(Long id);

}
