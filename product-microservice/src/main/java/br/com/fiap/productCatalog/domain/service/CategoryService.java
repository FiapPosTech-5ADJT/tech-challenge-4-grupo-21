package br.com.fiap.productCatalog.domain.service;

import br.com.fiap.productCatalog.domain.entity.Category;
import br.com.fiap.productCatalog.domain.entity.Product;
import br.com.fiap.productCatalog.domain.gateway.CategoryGateway;
import br.com.fiap.productCatalog.domain.gateway.ProductGateway;

import java.util.List;
import java.util.Optional;

public class CategoryService {

    private final CategoryGateway categoryGateway;

    public CategoryService(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category createCategory(Category category) {
        return categoryGateway.save(category);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryGateway.findById(id);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryGateway.findByName(name);
    }

    public List<Product> getProductsByCategoryId(Long id) {
        return categoryGateway.findProductsByCategoryId(id);
    }

    public List<Product> getProductsByCategoryName(String name) {
        return categoryGateway.findProductsByCategoryName(name);
    }

    public void addProductToCategory(Long categoryId, Product product) {
        Category category = categoryGateway.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        categoryGateway.addProductToCategory(categoryId, product);
    }

}