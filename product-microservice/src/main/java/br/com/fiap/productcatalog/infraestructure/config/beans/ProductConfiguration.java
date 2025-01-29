package br.com.fiap.productcatalog.infraestructure.config.beans;

import br.com.fiap.productcatalog.application.usecases.AddProductStockUseCase;
import br.com.fiap.productcatalog.application.usecases.GetProductStockUseCase;
import br.com.fiap.productcatalog.application.usecases.GetProductsUseCase;
import br.com.fiap.productcatalog.application.usecases.RemoveProductStockUseCase;
import br.com.fiap.productcatalog.domain.gateway.CategoryGateway;
import br.com.fiap.productcatalog.domain.gateway.ProductGateway;
import br.com.fiap.productcatalog.domain.service.ProductService;
import br.com.fiap.productcatalog.infraestructure.gateway.ProductGatewayImpl;
import br.com.fiap.productcatalog.infraestructure.persistence.converter.db.CategoryEntityConverter;
import br.com.fiap.productcatalog.infraestructure.persistence.converter.db.ProductEntityConverter;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

    @Bean
    ProductEntityConverter productEntityConverter(CategoryEntityConverter categoryEntityConverter) {
        return new ProductEntityConverter(categoryEntityConverter);
    }

    @Bean
    ProductGateway productGateway(ProductRepository productRepository, ProductEntityConverter productEntityConverter) {
        return new ProductGatewayImpl(productRepository, productEntityConverter);
    }

    @Bean
    ProductService productService(ProductGateway productGateway, CategoryGateway categoryGateway) {
        return new ProductService(productGateway, categoryGateway);
    }

    @Bean
    AddProductStockUseCase addProductStockUseCase(ProductService productService) {
        return new AddProductStockUseCase(productService);
    }

    @Bean
    RemoveProductStockUseCase removeProductStockUseCase(ProductService productService) {
        return new RemoveProductStockUseCase(productService);
    }

    @Bean
    GetProductStockUseCase getProductStockUseCase(ProductService productService) {
        return new GetProductStockUseCase(productService);
    }

    @Bean
    GetProductsUseCase getProductsUseCase(ProductService productService) {
        return new GetProductsUseCase(productService);
    }
}
