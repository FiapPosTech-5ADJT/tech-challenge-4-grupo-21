package br.com.fiap.productcatalog.infraestructure.batch;

import br.com.fiap.productcatalog.domain.entity.Category;
import br.com.fiap.productcatalog.domain.service.CategoryService;
import br.com.fiap.productcatalog.infraestructure.api.dto.ProductCSVDTO;
import br.com.fiap.productcatalog.infraestructure.persistence.converter.db.CategoryEntityConverter;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.entity.CategoryJPAEntity;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.entity.ProductJPAEntity;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@AllArgsConstructor
public class ProductProcessor implements ItemProcessor<ProductCSVDTO, ProductJPAEntity> {

    private CategoryService categoryService;
    private CategoryEntityConverter categoryEntityConverter;

    @Override
    public ProductJPAEntity process(ProductCSVDTO productCSVDTO) {
        try{
            CategoryJPAEntity categoryJPAEntity = categoryService.findByName(productCSVDTO.getCategoryName())
                    .map(categoryEntityConverter::toEntity)
                    .orElseGet(() -> {
                        Category savedCategory = categoryService.save(new Category(productCSVDTO.getCategoryName()));
                        return categoryEntityConverter.toEntity(savedCategory);
                    });
            ProductJPAEntity productJPAEntity = new ProductJPAEntity();
            productJPAEntity.setName(productCSVDTO.getName());
            productJPAEntity.setDescription(productCSVDTO.getDescription());
            productJPAEntity.setPrice(productCSVDTO.getPrice());
            productJPAEntity.setCategory(categoryJPAEntity);
            productJPAEntity.setStock(productCSVDTO.getQuantity());
            return productJPAEntity;
        }catch (Exception e){
            System.out.println("Erro ao processar o produto: " + productCSVDTO.getName()+ " - "+ e.getMessage());
        }
        return null;
    }
}
