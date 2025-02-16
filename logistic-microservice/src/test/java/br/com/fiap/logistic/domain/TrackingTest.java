package br.com.fiap.logistic.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class TrackingTest {

    private Tracking tracking;
    private Order order;
    private DeliveryPerson deliveryPerson;
    private LocalDateTime estimatedDelivery;

    @BeforeEach
    void setUp() {
        estimatedDelivery = LocalDateTime.now().plusDays(2);
        deliveryPerson = new DeliveryPerson(1L, "Carlos Motoboy", "XYZ-9876", true);
        order = new Order(1L, 100L, estimatedDelivery, null, "12345-678", deliveryPerson.getId());
        tracking = new Tracking(order.getId(), deliveryPerson.getId(), estimatedDelivery);
    }

    @Test
    void deveCriarTrackingComInformacoesCorretas() {
        assertThat(tracking.getOrderId()).isEqualTo(order.getId());
        assertThat(tracking.getDeliveryPersonId()).isEqualTo(deliveryPerson.getId());
        assertThat(tracking.getEstimatedDeliveryTime()).isEqualTo(estimatedDelivery);
        assertThat(tracking.getCreatedAt()).isNotNull();
    }

    @Test
    void deveAtualizarLatitudeELongitudeComHorarioDeAtualizacao() {
        tracking.updateLatitudeLongitude(new BigDecimal("40.7128"), new BigDecimal("-74.0060"));

        assertThat(tracking.getLatitude()).isEqualTo(new BigDecimal("40.7128"));
        assertThat(tracking.getLongitude()).isEqualTo(new BigDecimal("-74.0060"));
        assertThat(tracking.getTrackingTime()).isNotNull();
    }

    @Test
    void devePermitirAtualizarDataEstimadaDeEntrega() {
        LocalDateTime novaData = LocalDateTime.now().plusDays(3);
        tracking.setEstimatedDeliveryTime(novaData);

        assertThat(tracking.getEstimatedDeliveryTime()).isEqualTo(novaData);
    }

    @Test
    void naoDevePermitirAtualizarDataEstimadaNula() {
        assertThatThrownBy(() -> tracking.setEstimatedDeliveryTime(null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("A data de entrega estimada não pode ser nula");
    }

    @Test
    void naoDevePermitirAtualizarDataEstimadaParaPassado() {
        LocalDateTime dataPassada = LocalDateTime.now().minusDays(1);

        assertThatThrownBy(() -> tracking.setEstimatedDeliveryTime(dataPassada))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("A data de entrega estimada não pode ser no passado");
    }
}
