package br.com.fiap.productcatalog.infraestructure.api.controller;

import br.com.fiap.productcatalog.application.usecases.GetCategoriesUseCase;
import br.com.fiap.productcatalog.domain.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final GetCategoriesUseCase getCategoriesUseCase;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(getCategoriesUseCase.getCategories());
    }
}
