package br.com.fiap.logistic.repository;

import br.com.fiap.logistic.entity.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<TrackingEntity, Long> {
}
