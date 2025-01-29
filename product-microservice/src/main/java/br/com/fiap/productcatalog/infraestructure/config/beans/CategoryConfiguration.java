package br.com.fiap.productcatalog.infraestructure.config.beans;

import br.com.fiap.productcatalog.application.usecases.GetCategoriesUseCase;
import br.com.fiap.productcatalog.domain.gateway.CategoryGateway;
import br.com.fiap.productcatalog.domain.service.CategoryService;
import br.com.fiap.productcatalog.infraestructure.gateway.CategoryGatewayImpl;
import br.com.fiap.productcatalog.infraestructure.persistence.converter.db.CategoryEntityConverter;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.repository.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfiguration {

    @Bean
    CategoryEntityConverter categoryEntityConverter() {
        return new CategoryEntityConverter();
    }

    @Bean
    CategoryGateway categoryGateway(CategoryRepository categoryRepository, CategoryEntityConverter categoryEntityConverter) {
        return new CategoryGatewayImpl(categoryRepository, categoryEntityConverter);
    }

    @Bean
    GetCategoriesUseCase getCategoriesUseCase(CategoryService categoryService) {
        return new GetCategoriesUseCase(categoryService);
    }

    @Bean
    CategoryService categoryService(CategoryGateway categoryGateway) {
        return new CategoryService(categoryGateway);
    }
}
