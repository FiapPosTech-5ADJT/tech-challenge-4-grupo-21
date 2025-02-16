package br.com.fiap.logistic.persistence;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.adapter.OrderConverter;
import br.com.fiap.logistic.adapter.TrackingConverter;
import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import br.com.fiap.logistic.entity.OrderEntity;
import br.com.fiap.logistic.entity.TrackingEntity;
import br.com.fiap.logistic.repository.DeliveryPersonRepository;
import br.com.fiap.logistic.repository.OrderRepository;
import br.com.fiap.logistic.repository.TrackingRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class TrackingRepositoryIntegratedTest {

    @Autowired
    private TrackingRepository trackingRepository;

    @Autowired
    private TrackingConverter trackingConverter;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private DeliveryPersonConverter deliveryPersonConverter;

    private OrderEntity orderEntity;
    private DeliveryPersonEntity deliveryPersonEntity;
    private Tracking tracking;

    @BeforeEach
    void setUp() {
        createAndSaveDeliveryPerson(new DeliveryPerson("João", "ABC-1234"));
        createAndSaveOrder(new Order(1L,
                LocalDateTime.now(),
                "12345-678",
                deliveryPersonConverter.convertToDomain(deliveryPersonEntity).getId()));
        tracking = new Tracking(orderConverter.convertToDomain(orderEntity).getId(),
                deliveryPersonConverter.convertToDomain(deliveryPersonEntity).getId(),
                LocalDateTime.now());
    }

    void createAndSaveDeliveryPerson(DeliveryPerson deliveryPerson) {
        DeliveryPersonEntity deliveryPersonEntityMethod = deliveryPersonConverter.convertToEntity(deliveryPerson);
        this.deliveryPersonEntity = deliveryPersonRepository.save(deliveryPersonEntityMethod);
    }

    void createAndSaveOrder(Order order) {
        OrderEntity orderEntityMethod = orderConverter.convertToEntity(order);
        orderEntityMethod.setDeliveryPerson(deliveryPersonEntity);
        this.orderEntity = orderRepository.save(orderEntityMethod);
    }

    @Test
    void criarRegistroComSucesso() {
        TrackingEntity trackingEntity = trackingConverter.toEntity(tracking);
        trackingEntity.setDeliveryPerson(deliveryPersonEntity);
        trackingEntity.setOrder(orderEntity);
        TrackingEntity saved = trackingRepository.save(trackingEntity);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertThat(saved.getOrder().getId()).isEqualTo(orderEntity.getId());
    }

    @Test
    void deveRetornarListaDeTracking() {
        TrackingEntity trackingEntity = trackingConverter.toEntity(tracking);
        trackingEntity.setDeliveryPerson(deliveryPersonEntity);
        trackingEntity.setOrder(orderEntity);
        trackingRepository.save(trackingEntity);

        assertThat(trackingRepository.findAll()).isNotEmpty();
        assertThat(trackingRepository.findAll()).hasSize(6);
        assertThat(trackingRepository.findAll().stream().anyMatch(t -> t.getOrder().getId().equals(orderEntity.getId()))).isTrue();
    }

    @Test
    void buscarListaDeTrackingPorLatitudeLongitude() {
        TrackingEntity trackingEntity = trackingConverter.toEntity(tracking);
        trackingEntity.setLatitude(BigDecimal.valueOf(84.599498));
        trackingEntity.setLongitude(BigDecimal.valueOf(-146.568067));
        trackingEntity.setDeliveryPerson(deliveryPersonEntity);
        trackingEntity.setOrder(orderEntity);
        trackingRepository.save(trackingEntity);

        assertThat(trackingRepository.findByLatitudeAndLongitude(trackingEntity.getLatitude(), trackingEntity.getLongitude())).isNotEmpty();
    }

}
