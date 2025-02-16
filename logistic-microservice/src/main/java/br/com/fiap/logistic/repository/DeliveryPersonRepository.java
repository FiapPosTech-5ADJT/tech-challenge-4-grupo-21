package br.com.fiap.logistic.repository;

import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPersonEntity, Long> {
    @Query("SELECT o.deliveryPerson FROM OrderEntity o WHERE o.id = :orderId")
    Optional<DeliveryPersonEntity> findDeliveryPersonByOrderId(@Param("orderId") Long orderId);
}
