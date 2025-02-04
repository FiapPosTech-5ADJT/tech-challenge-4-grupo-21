package br.com.fiap.productcatalog.domain.gateway;

import br.com.fiap.productcatalog.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductGateway {

    Product findById(Long id);

    BigDecimal getProductStock(Long id);

    List<Product> findAll();

    void addStock(Product product);

    void removeStock(Product product);

}
