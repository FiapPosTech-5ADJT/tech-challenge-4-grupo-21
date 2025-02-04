package br.com.fiap.productcatalog.domain.service;

import br.com.fiap.productcatalog.domain.entity.Category;
import br.com.fiap.productcatalog.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public class TestDataFactory {

    public static Category createCategory(Long id, String name) {
        return new Category(id, name);
    }

    public static Product createProduct(Long id, String name, String description, double price, Category category, double stock) {
        return new Product(id, name, description, BigDecimal.valueOf(price), category, BigDecimal.valueOf(stock));
    }

    public static List<Product> createProductList() {
        Category category1 = createCategory(1L, "Category 1");
        Category category2 = createCategory(2L, "Category 2");

        return List.of(
                createProduct(1L, "Product 1", "Description 1", 10.0, category1, 5.0),
                createProduct(2L, "Product 2", "Description 2", 20.0, category2, 8.0)
        );
    }

    public static List<Category> createCategoryList() {
        return List.of(
                createCategory(1L, "Category 1"),
                createCategory(2L, "Category 2")
        );
    }
}
