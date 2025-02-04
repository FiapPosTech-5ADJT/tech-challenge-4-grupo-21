package br.com.fiap.productcatalog.infraestructure.persistence.converter.db;

import br.com.fiap.productcatalog.domain.entity.Category;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.entity.CategoryJPAEntity;

public class CategoryEntityConverter implements EntityConverter<Category, CategoryJPAEntity> {

    @Override
    public CategoryJPAEntity toEntity(Category domainObj) {
        return new CategoryJPAEntity(
                domainObj.getId(),
                domainObj.getName()
        );
    }

    @Override
    public Category toDomainObj(CategoryJPAEntity categoryEntity) {
        return new Category(
                categoryEntity.getId(),
                categoryEntity.getName()
        );
    }
}
