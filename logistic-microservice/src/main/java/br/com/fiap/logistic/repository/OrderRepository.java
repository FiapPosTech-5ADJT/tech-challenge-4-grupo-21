package br.com.fiap.logistic.repository;

import br.com.fiap.logistic.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> getReferenceByZipCode(String zipCode);
    Optional<OrderEntity> findByExternalId(Long externalId);
    List<OrderEntity> findByDeliveryPersonId(Long deliveryPersonId);
}
