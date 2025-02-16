package br.com.fiap.logistic.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class OrderTest {

    private Order order;
    private LocalDateTime estimatedDelivery;

    @BeforeEach
    void setUp() {
        estimatedDelivery = LocalDateTime.now().plusDays(2);
        order = new Order(1L, 100L, estimatedDelivery, null, "12345-678", 1L);
    }

    @Test
    void deveCriarOrderComStatusPendente() {
        assertThat(order.getId()).isEqualTo(1L);
        assertThat(order.getOrderExternalId()).isEqualTo(100L);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(order.getEstimatedDelivery()).isEqualTo(estimatedDelivery);
        assertThat(order.getZipCode()).isEqualTo("12345-678");
    }

    @Test
    void devePermitirAtualizarStatusParaEmTransito() {
        order.setStatus(OrderStatus.IN_TRANSIT);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.IN_TRANSIT);
    }

    @Test
    void naoDevePermitirCompletarPedidoDiretoDePendente() {
        assertThatThrownBy(() -> order.setStatus(OrderStatus.COMPLETED))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Order can't be completed without being in transit");
    }

    @Test
    void naoDevePermitirVoltarParaPendenteAposTransito() {
        order.setStatus(OrderStatus.IN_TRANSIT);

        assertThatThrownBy(() -> order.setStatus(OrderStatus.PENDING))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Order can't be pending after being in transit");
    }

    @Test
    void naoDevePermitirAlterarStatusSePedidoJaFoiConcluido() {
        order.setStatus(OrderStatus.IN_TRANSIT);
        order.setStatus(OrderStatus.COMPLETED);

        assertThatThrownBy(() -> order.setStatus(OrderStatus.PENDING))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Order already completed");
    }

    @Test
    void naoDevePermitirAlterarStatusSePedidoJaFoiCancelado() {
        order.setStatus(OrderStatus.CANCELED);

        assertThatThrownBy(() -> order.setStatus(OrderStatus.IN_TRANSIT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Order already canceled");
    }

    @Test
    void deveLancarErroSeZipCodeForInvalido() {
        assertThatThrownBy(() -> new Order(1L, 100L, estimatedDelivery, null, "1234-567", 1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid zip code");
    }

    @Test
    void devePermitirCriarOrderComZipCodeValido() {
        assertThatCode(() -> new Order(1L, 100L, estimatedDelivery, null, "12345-678", 1L))
                .doesNotThrowAnyException();
    }
}
