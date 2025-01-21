package br.com.fiap.productCatalog.domain.gateway;

import br.com.fiap.productCatalog.domain.entity.Category;
import br.com.fiap.productCatalog.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {

    Category save(Category category);

    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);

    List<Product> findProductsByCategoryId(Long id);

    List<Product> findProductsByCategoryName(String name);

    void addProductToCategory(Long categoryId, Product product);

}
