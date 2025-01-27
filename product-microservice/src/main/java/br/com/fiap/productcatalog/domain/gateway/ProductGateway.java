package br.com.fiap.productcatalog.domain.gateway;

import br.com.fiap.productcatalog.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductGateway {

    Product save(Product product);

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    void deleteById(Long id);

    BigDecimal getProductStock(Long id);

    List<Product> findAll();

}
