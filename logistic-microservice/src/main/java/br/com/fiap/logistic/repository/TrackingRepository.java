package br.com.fiap.logistic.repository;

import br.com.fiap.logistic.entity.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TrackingRepository extends JpaRepository<TrackingEntity, Long> {
    Optional<List<TrackingEntity>> findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);
}
