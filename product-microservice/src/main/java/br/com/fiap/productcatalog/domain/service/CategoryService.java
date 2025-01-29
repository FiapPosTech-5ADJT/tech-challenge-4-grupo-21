package br.com.fiap.productcatalog.domain.service;

import br.com.fiap.productcatalog.domain.entity.Category;
import br.com.fiap.productcatalog.domain.gateway.CategoryGateway;

import java.util.List;

public class CategoryService {

    private final CategoryGateway categoryGateway;

    public CategoryService(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public List<Category> getCategories() {
        return categoryGateway.findAll();
    }
}