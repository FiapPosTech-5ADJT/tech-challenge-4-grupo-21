package br.com.fiap.logistic.repository;

import br.com.fiap.logistic.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> getReferenceByZipCode(String zipCode);
}
