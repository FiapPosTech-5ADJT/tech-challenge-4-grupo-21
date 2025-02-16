package br.com.fiap.logistic.service;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.adapter.OrderConverter;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.OrderStatus;
import br.com.fiap.logistic.entity.OrderEntity;
import br.com.fiap.logistic.gateway.OrderGateway;
import br.com.fiap.logistic.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private OrderConverter orderConverter;

    @Mock
    private DeliveryPersonConverter deliveryPersonConverter;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private OrderEntity orderEntity;

    @BeforeEach
    void setup() {
        order = new Order(1L, 1L, LocalDateTime.now().plusDays(2),LocalDateTime.now(), "12345-678", 1L);
        orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setExternalId(1L);
        orderEntity.setEstimatedDelivery(LocalDateTime.now().plusDays(2));
        orderEntity.setCreatedAt(LocalDateTime.now());
        orderEntity.setZipCode("12345-678");
    }

    @Test
    void deveCriarPedidoComSucesso() {
        when(orderConverter.convertToEntity(order)).thenReturn(orderEntity);
        when(orderGateway.save(orderEntity)).thenReturn(orderEntity);
        when(orderConverter.convertToDomain(orderEntity)).thenReturn(order);

        Order result = orderService.createOrder(order);

        assertNotNull(result);
        assertEquals(order.getId(), result.getId());
        verify(orderGateway, times(1)).save(orderEntity);
    }

    @Test
    void deveRetornarPedidoPorId() {
        when(orderGateway.getOrderByExternalId(1L)).thenReturn(Optional.of(orderEntity));
        when(orderConverter.convertToDomain(orderEntity)).thenReturn(order);

        Optional<Order> result = orderService.getOrderById(1L);

        assertTrue(result.isPresent());
        assertEquals(order.getId(), result.get().getId());
        verify(orderGateway, times(1)).getOrderByExternalId(1L);
    }

    @Test
    void deveLancarExcecaoAoBuscarPedidoInexistente() {
        when(orderGateway.getOrderByExternalId(2L)).thenReturn(Optional.empty());

        Optional<Order> result = orderService.getOrderById(2L);

        assertFalse(result.isPresent());
    }

    @Test
    void deveLancarExcecaoAoAtualizarPedidoSemPassarPeloStatusEmTransito() {
        when(orderGateway.getOrderByExternalId(1L)).thenReturn(Optional.of(orderEntity));
        when(orderConverter.convertToDomain(orderEntity)).thenReturn(order);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.updateOrderStatus(1L, OrderStatus.COMPLETED);
        });

        assertEquals("Order can't be completed without being in transit", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoAoAtualizarStatusDePedidoInexistente() {
        when(orderGateway.getOrderByExternalId(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.updateOrderStatus(2L, OrderStatus.COMPLETED);
        });

        assertEquals("Order not found", exception.getMessage());
    }

    @Test
    void deveRetornarPedidosPorCEP() {
        List<OrderEntity> orderEntities = List.of(orderEntity);
        List<Order> orders = List.of(order);

        when(orderGateway.getOrdersByZipCode("12345-678")).thenReturn(orderEntities);
        when(orderConverter.convertToDomain(orderEntity)).thenReturn(order);

        List<Order> result = orderService.getOrdersByZipCode("12345-678");

        assertEquals(orders.size(), result.size());
        verify(orderGateway, times(1)).getOrdersByZipCode("12345-678");
    }
}
