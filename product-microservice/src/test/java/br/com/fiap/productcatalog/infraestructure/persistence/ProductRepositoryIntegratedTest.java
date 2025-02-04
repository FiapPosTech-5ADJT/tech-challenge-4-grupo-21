package br.com.fiap.productcatalog.infraestructure.persistence;

import br.com.fiap.productcatalog.domain.entity.Category;
import br.com.fiap.productcatalog.domain.entity.Product;
import br.com.fiap.productcatalog.infraestructure.persistence.converter.db.CategoryEntityConverter;
import br.com.fiap.productcatalog.infraestructure.persistence.converter.db.ProductEntityConverter;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.entity.ProductJPAEntity;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.repository.CategoryRepository;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class ProductRepositoryIntegratedTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEntityConverter productEntityConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryEntityConverter categoryEntityConverter;

    private Product product;
    private Category category;

    @BeforeEach
    void setup() {
        category = new Category("Category 1");
        var categoryJpa = categoryRepository.save(categoryEntityConverter.toEntity(category));
        product = new Product("Product 1",
                "Description 1",
                BigDecimal.TEN,
                categoryEntityConverter.toDomainObj(categoryJpa),
                BigDecimal.TEN);
    }

    @Test
    void givenProduct_whenSaved_thenShouldCreateSuccessfully() {
        var productJpa = productEntityConverter.toEntity(product);
        var productSaved = productRepository.save(productJpa);

        assertThat(productSaved).isNotNull()
                .isInstanceOf(ProductJPAEntity.class);
        assertThat(productSaved.getName()).isSameAs(product.getName());
        assertThat(productSaved.getDescription()).isSameAs(product.getDescription());
        assertThat(productSaved.getPrice()).isEqualTo(product.getPrice());
        assertThat(productSaved.getCategory().getName()).isSameAs(category.getName());
    }

    @Test
    void givenProduct_whenFindById_thenShouldReturnProduct() {
        var productJpa = productEntityConverter.toEntity(product);
        var productSaved = productRepository.save(productJpa);

        Optional<ProductJPAEntity> foundProduct = productRepository.findById(productSaved.getId());

        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isSameAs(product.getName());
    }

    @Test
    void givenProduct_whenUpdated_thenShouldPersistChanges() {
        var productJpa = productEntityConverter.toEntity(product);
        var productSaved = productRepository.save(productJpa);

        // Atualizando o produto
        productSaved.setName("Updated Product Name");
        productSaved.setDescription("Updated Description");

        var updatedProduct = productRepository.save(productSaved);

        assertThat(updatedProduct.getName()).isSameAs("Updated Product Name");
        assertThat(updatedProduct.getDescription()).isSameAs("Updated Description");
    }

    @Test
    void givenProduct_whenDeleted_thenShouldNotExistInDatabase() {
        var productJpa = productEntityConverter.toEntity(product);
        var productSaved = productRepository.save(productJpa);

        productRepository.delete(productSaved);
        Optional<ProductJPAEntity> deletedProduct = productRepository.findById(productSaved.getId());

        assertThat(deletedProduct).isNotPresent();
    }

    @Test
    void givenProductSavedInDatabase_whenJpaToDomain_thenShouldReturnProduct() {
        var productJpa = productEntityConverter.toEntity(product);
        var productSaved = productRepository.save(productJpa);

        var productDomain = productEntityConverter.toDomainObj(productSaved);

        assertThat(productDomain).isNotNull()
                .isInstanceOf(Product.class);
        assertThat(productDomain.getName()).isSameAs(product.getName());
        assertThat(productDomain.getDescription()).isSameAs(product.getDescription());
        assertThat(productDomain.getPrice()).isEqualTo(product.getPrice());
        assertThat(productDomain.getCategory().getName()).isSameAs(category.getName());
    }
}

