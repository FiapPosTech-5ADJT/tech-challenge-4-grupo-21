package br.com.fiap.logistic.repository;

import br.com.fiap.logistic.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
