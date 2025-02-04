package br.com.fiap.productcatalog.infraestructure.gateway;

import br.com.fiap.productcatalog.domain.entity.Category;
import br.com.fiap.productcatalog.domain.gateway.CategoryGateway;
import br.com.fiap.productcatalog.infraestructure.persistence.converter.db.CategoryEntityConverter;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.repository.CategoryRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CategoryGatewayImpl implements CategoryGateway {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityConverter categoryEntityConverter;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll().stream().map(categoryEntityConverter::toDomainObj).toList();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id).map(categoryEntityConverter::toDomainObj);
    }

    @Override
    public Category save(Category category) {
        return categoryEntityConverter.toDomainObj(categoryRepository.save(categoryEntityConverter.toEntity(category)));
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name).map(categoryEntityConverter::toDomainObj);
    }
}
