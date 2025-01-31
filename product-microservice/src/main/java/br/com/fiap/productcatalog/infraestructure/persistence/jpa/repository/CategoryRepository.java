package br.com.fiap.productcatalog.infraestructure.persistence.jpa.repository;

import br.com.fiap.productcatalog.infraestructure.persistence.jpa.entity.CategoryJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryJPAEntity, Long> {

    Optional<CategoryJPAEntity> findByName(String name);
}
