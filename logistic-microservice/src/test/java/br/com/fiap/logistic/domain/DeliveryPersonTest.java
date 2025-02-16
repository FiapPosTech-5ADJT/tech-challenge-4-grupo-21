package br.com.fiap.logistic.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

class DeliveryPersonTest {

    private DeliveryPerson deliveryPerson;

    @BeforeEach
    void setUp() {
        deliveryPerson = new DeliveryPerson(1L, "João Entregador", "ABC-1234", true);
    }

    @Test
    void deveCriarEntregadorComInformacoesCorretas() {
        assertThat(deliveryPerson.getId()).isEqualTo(1L);
        assertThat(deliveryPerson.getName()).isEqualTo("João Entregador");
        assertThat(deliveryPerson.getVehiclePlate()).isEqualTo("ABC-1234");
        assertThat(deliveryPerson.isAvailable()).isTrue();
    }
}