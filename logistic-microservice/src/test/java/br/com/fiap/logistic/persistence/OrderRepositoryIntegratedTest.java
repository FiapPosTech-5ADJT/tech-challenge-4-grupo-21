package br.com.fiap.logistic.persistence;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.adapter.OrderConverter;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.entity.OrderEntity;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import br.com.fiap.logistic.repository.OrderRepository;
import br.com.fiap.logistic.repository.DeliveryPersonRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class OrderRepositoryIntegratedTest {

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

    @BeforeEach
    void setUp() {
        // Criação e salvamento do DeliveryPerson
        deliveryPersonEntity = new DeliveryPersonEntity();
        deliveryPersonEntity.setName("João");
        deliveryPersonEntity.setVehiclePlate("12345-678");
        deliveryPersonEntity.setAvailable(true);
        deliveryPersonEntity = deliveryPersonRepository.save(deliveryPersonEntity);

        Order order = new Order(1L,
                LocalDateTime.now(),
                "12345-678",
                deliveryPersonConverter.convertToDomain(deliveryPersonEntity).getId());
        orderEntity = orderConverter.convertToEntity(order);
        orderEntity.setDeliveryPerson(deliveryPersonEntity);
        orderEntity = orderRepository.save(orderEntity);
    }

    @Test
    void criarOrderComSucesso() {
        assertNotNull(orderEntity);
        assertNotNull(orderEntity.getId());
        assertThat(orderEntity.getDeliveryPerson().getId()).isEqualTo(deliveryPersonEntity.getId());
    }

    @Test
    void deveRetornarListaDeOrders() {
        orderRepository.save(orderEntity);
        assertThat(orderRepository.findAll()).isNotEmpty();
        assertThat(orderRepository.findAll()).hasSizeGreaterThan(1);
        assertThat(orderRepository.findAll().stream().anyMatch(o -> o.getId().equals(orderEntity.getId()))).isTrue();
    }

    @Test
    void buscarOrderPorZipCode() {
        orderEntity.setZipCode("12345-678");
        orderRepository.save(orderEntity);
        assertThat(orderRepository.getReferenceByZipCode("12345-678")).isNotEmpty();
        assertThat(orderRepository.getReferenceByZipCode("12345-678")).hasSize(5);
    }

    @Test
    void buscarOrderPorDeliveryPerson() {
        orderRepository.save(orderEntity);
        assertThat(orderRepository.findAll().stream().anyMatch(o -> o.getDeliveryPerson().equals(deliveryPersonEntity))).isTrue();
    }

}
