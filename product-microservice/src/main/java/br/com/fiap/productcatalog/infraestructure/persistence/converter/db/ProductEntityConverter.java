package br.com.fiap.productcatalog.infraestructure.persistence.converter.db;

import br.com.fiap.productcatalog.domain.entity.Product;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.entity.ProductJPAEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductEntityConverter implements EntityConverter<Product, ProductJPAEntity> {

    private final CategoryEntityConverter categoryEntityConverter;

    @Override
    public ProductJPAEntity toEntity(Product domainObj) {
        return new ProductJPAEntity(
                domainObj.getId(),
                domainObj.getName(),
                domainObj.getDescription(),
                domainObj.getPrice(),
                categoryEntityConverter.toEntity(domainObj.getCategory()),
                domainObj.getStock()
        );
    }

    @Override
    public Product toDomainObj(ProductJPAEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                categoryEntityConverter.toDomainObj(productEntity.getCategory()),
                productEntity.getStock()
        );
    }
}
