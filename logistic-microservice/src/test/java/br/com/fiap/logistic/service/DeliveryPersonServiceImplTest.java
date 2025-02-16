package br.com.fiap.logistic.service;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.adapter.OrderConverter;
import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.OrderStatus;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import br.com.fiap.logistic.gateway.DeliveryPersonGateway;
import br.com.fiap.logistic.service.impl.DeliveryPersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeliveryPersonServiceImplTest {

    @Mock
    private DeliveryPersonGateway deliveryPersonGateway;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderConverter orderConverter;

    @Mock
    private DeliveryPersonConverter deliveryPersonConverter;

    @InjectMocks
    private DeliveryPersonServiceImpl deliveryPersonService;

    private DeliveryPerson deliveryPerson;
    private DeliveryPersonEntity deliveryPersonEntity;
    private Order order;

    @BeforeEach
    void setup() {
        deliveryPerson = new DeliveryPerson(1L, "João Silva", "ABC1234", true);
        deliveryPersonEntity = new DeliveryPersonEntity();
        order = new Order(1L, 1L, LocalDateTime.now().plusDays(2),LocalDateTime.now(), "12345-678", deliveryPerson.getId());
    }

    @Test
    void deveCriarUmEntregador() {
        when(deliveryPersonConverter.convertToEntity(deliveryPerson)).thenReturn(deliveryPersonEntity);
        when(deliveryPersonGateway.save(deliveryPersonEntity)).thenReturn(deliveryPersonEntity);
        when(deliveryPersonConverter.convertToDomain(deliveryPersonEntity)).thenReturn(deliveryPerson);

        DeliveryPerson resultado = deliveryPersonService.createDeliveryPerson(deliveryPerson);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getName());
        verify(deliveryPersonGateway, times(1)).save(deliveryPersonEntity);
    }

    @Test
    void deveLancarExcecaoQuandoEntregadorNaoForEncontrado() {
        when(deliveryPersonGateway.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                deliveryPersonService.assignOrderToDeliveryPerson(1L, 1L, "12345-678"));

        assertEquals("Entregador não encontrado", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoEntregadorNaoEstiverDisponivel() {
        deliveryPerson = new DeliveryPerson(1L, "João Silva", "ABC1234", false);
        when(deliveryPersonGateway.findById(1L)).thenReturn(Optional.of(deliveryPersonEntity));
        when(deliveryPersonConverter.convertToDomain(deliveryPersonEntity)).thenReturn(deliveryPerson);

        Exception exception = assertThrows(RuntimeException.class, () ->
                deliveryPersonService.assignOrderToDeliveryPerson(1L, 1L, "12345-678"));

        assertEquals("Entregador não disponível", exception.getMessage());
    }

    @Test
    void deveConcluirEntregaComSucesso() {
        when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));
        when(deliveryPersonGateway.findById(1L)).thenReturn(Optional.of(deliveryPersonEntity));
        when(deliveryPersonConverter.convertToDomain(deliveryPersonEntity)).thenReturn(deliveryPerson);

        deliveryPersonService.completeDelivery(1L);

        verify(orderService, times(1)).updateOrderStatus(1L, OrderStatus.COMPLETED);
    }
}
