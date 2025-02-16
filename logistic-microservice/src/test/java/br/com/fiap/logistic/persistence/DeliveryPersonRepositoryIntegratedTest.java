package br.com.fiap.logistic.persistence;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.adapter.OrderConverter;
import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import br.com.fiap.logistic.entity.OrderEntity;
import br.com.fiap.logistic.repository.DeliveryPersonRepository;
import br.com.fiap.logistic.repository.OrderRepository;
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
class DeliveryPersonRepositoryIntegratedTest {

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    DeliveryPersonConverter deliveryPersonConverter;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConverter orderConverter;

    private DeliveryPersonEntity deliveryPersonEntity;
    private DeliveryPerson deliveryPerson;



    @BeforeEach
    void setUp() {
        deliveryPerson = new DeliveryPerson("João", "ABC-1234");
        deliveryPersonEntity = deliveryPersonConverter.convertToEntity(deliveryPerson);
    }

    @Test
    void criarRegistroComSucesso() {
        DeliveryPersonEntity deliveryPersonEntitySaved = deliveryPersonRepository.save(deliveryPersonEntity);
        assertNotNull(deliveryPersonEntitySaved);
        assertThat(deliveryPersonEntitySaved.getId()).isNotNull();
        assertThat(deliveryPersonEntitySaved.getName()).isEqualTo(deliveryPerson.getName());
    }

    @Test
    void deveRetornarListaDeDeliveryPerson() {
        deliveryPersonRepository.save(deliveryPersonEntity);
        assertThat(deliveryPersonRepository.findAll()).isNotEmpty();
        assertThat(deliveryPersonRepository.findAll()).hasSize(16);
        assertThat(deliveryPersonRepository.findAll().stream().anyMatch(t -> t.getId().equals(deliveryPersonEntity.getId()))).isTrue();
    }

    @Test
    void deveRetornarDeliveryPersonPassandoIdPedido() {
        deliveryPersonEntity = deliveryPersonRepository.save(deliveryPersonEntity);
        Order order = new Order(1L, LocalDateTime.now(), "12345-678", deliveryPersonConverter.convertToDomain(deliveryPersonEntity).getId());
        OrderEntity orderEntity = orderConverter.convertToEntity(order);
        orderEntity.setDeliveryPerson(deliveryPersonEntity);
        OrderEntity orderEntitySaved = orderRepository.save(orderEntity);
        assertThat(deliveryPersonRepository.findDeliveryPersonByOrderId(orderEntitySaved.getId())).isNotEmpty();
    }
}
