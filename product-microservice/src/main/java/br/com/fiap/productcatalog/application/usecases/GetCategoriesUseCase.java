package br.com.fiap.productcatalog.application.usecases;

import br.com.fiap.productcatalog.domain.entity.Category;
import br.com.fiap.productcatalog.domain.service.CategoryService;

import java.util.List;

public class GetCategoriesUseCase {

    private final CategoryService categoryService;

    public GetCategoriesUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Category> getCategories() {
        return categoryService.getCategories();
    }
}
