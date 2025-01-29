package br.com.fiap.productcatalog.domain.gateway;

import br.com.fiap.productcatalog.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {

    List<Category> findAll();

    Optional<Category> findById(Long id);
}
