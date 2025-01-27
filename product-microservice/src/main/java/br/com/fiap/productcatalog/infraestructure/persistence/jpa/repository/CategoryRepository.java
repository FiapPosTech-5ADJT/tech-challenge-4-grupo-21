package br.com.fiap.productcatalog.infraestructure.persistence.jpa.repository;

import br.com.fiap.productcatalog.infraestructure.persistence.jpa.entity.CategoryJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryJPAEntity, Long> {
}
