package br.com.fiap.productcatalog.domain.service;

import br.com.fiap.productcatalog.domain.entity.Category;
import br.com.fiap.productcatalog.domain.gateway.CategoryGateway;

import java.util.List;
import java.util.Optional;

public class CategoryService {

    private final CategoryGateway categoryGateway;

    public CategoryService(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public List<Category> getCategories() {
        return categoryGateway.findAll();
    }

    public Category save(Category category) {
        return categoryGateway.save(category);
    }

    public Optional<Category> findByName(String name) {
        return categoryGateway.findByName(name);
    }
}