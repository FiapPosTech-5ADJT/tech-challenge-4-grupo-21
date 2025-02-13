package br.com.fiap.logistic.repository;

import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPersonEntity, Long> {
    Optional<DeliveryPersonEntity> findByOrderId(Long orderId);
}
