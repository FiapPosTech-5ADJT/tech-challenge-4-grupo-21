package br.com.fiap.productcatalog.infraestructure.persistence.jpa.repository;

import br.com.fiap.productcatalog.infraestructure.persistence.jpa.entity.ProductJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductJPAEntity, Long> {
}
