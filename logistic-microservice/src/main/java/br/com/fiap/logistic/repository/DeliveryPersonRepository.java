package br.com.fiap.logistic.repository;

import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPersonEntity, Long> {
}
