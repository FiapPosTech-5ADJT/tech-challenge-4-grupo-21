package br.com.fiap.logistic.service;

import br.com.fiap.logistic.adapter.TrackingConverter;
import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.entity.TrackingEntity;
import br.com.fiap.logistic.gateway.TrackingGateway;
import br.com.fiap.logistic.service.impl.TrackingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackingServiceImplTest {

    @Mock
    private TrackingGateway trackingGateway;

    @Mock
    private TrackingConverter trackingConverter;

    @InjectMocks
    private TrackingServiceImpl trackingService;

    private Tracking tracking;
    private TrackingEntity trackingEntity;
    private DeliveryPerson deliveryPerson;

    @BeforeEach
    void setup() {
        deliveryPerson = new DeliveryPerson(1L, "João", "ABC-1234", true);
        Order order = new Order(1L, LocalDateTime.now(), "12345-678", deliveryPerson.getId());
        tracking = new Tracking(order.getId(), deliveryPerson.getId(), LocalDateTime.now());
        trackingEntity = new TrackingEntity();
        trackingEntity.setId(1L);
    }

    @Test
    void deveCriarTrackingComSucesso() {
        when(trackingConverter.toEntity(tracking)).thenReturn(trackingEntity);
        when(trackingGateway.createTracking(trackingEntity)).thenReturn(trackingEntity);
        when(trackingConverter.toDomain(trackingEntity)).thenReturn(tracking);

        Tracking result = trackingService.createTracking(tracking);

        assertNotNull(result);
        verify(trackingGateway, times(1)).createTracking(trackingEntity);
    }

    @Test
    void deveAtualizarLatitudeELongitudeComSucesso() {
        when(trackingGateway.getTrackingByOrderId(1L)).thenReturn(Optional.of(trackingEntity));
        when(trackingConverter.toDomain(trackingEntity)).thenReturn(tracking);
        when(trackingConverter.toEntity(tracking)).thenReturn(trackingEntity);
        doNothing().when(trackingGateway).updateTrackingLatitudeAndLongitude(any(TrackingEntity.class));

        trackingService.updateTrackingLatitudeAndLongitude(1L, BigDecimal.ONE, BigDecimal.TEN);

        verify(trackingGateway, times(1)).updateTrackingLatitudeAndLongitude(any(TrackingEntity.class));
    }

    @Test
    void deveLancarErroAoAtualizarLatitudeELongitudeComValoresNulos() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                trackingService.updateTrackingLatitudeAndLongitude(1L, null, BigDecimal.TEN));
        assertEquals("Latitude e longitude são obrigatórios", exception.getMessage());
    }

    @Test
    void deveRetornarTrackingPorOrderId() {
        when(trackingGateway.getTrackingByOrderId(1L)).thenReturn(Optional.of(trackingEntity));
        when(trackingConverter.toDomain(trackingEntity)).thenReturn(tracking);

        Tracking result = trackingService.getTrackingByOrderId(1L);

        assertNotNull(result);
        verify(trackingGateway, times(1)).getTrackingByOrderId(1L);
    }

    @Test
    void deveLancarErroQuandoTrackingNaoForEncontradoPorOrderId() {
        when(trackingGateway.getTrackingByOrderId(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                trackingService.getTrackingByOrderId(1L));
        assertEquals("Tracking não encontrado", exception.getMessage());
    }

    @Test
    void deveRetornarTrackingPorLatitudeELongitude() {
        List<TrackingEntity> entityList = List.of(trackingEntity);
        when(trackingGateway.getTrackingByLatitudeAndLongitude(BigDecimal.ONE, BigDecimal.TEN))
                .thenReturn(Optional.of(entityList));
        when(trackingConverter.toDomain(trackingEntity)).thenReturn(tracking);

        List<Tracking> result = trackingService.getTrackingByLatitudeAndLongitude(BigDecimal.ONE, BigDecimal.TEN);

        assertEquals(1, result.size());
        verify(trackingGateway, times(1)).getTrackingByLatitudeAndLongitude(BigDecimal.ONE, BigDecimal.TEN);
    }

    @Test
    void deveLancarErroQuandoTrackingNaoForEncontradoPorLatitudeELongitude() {
        when(trackingGateway.getTrackingByLatitudeAndLongitude(BigDecimal.ONE, BigDecimal.TEN))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                trackingService.getTrackingByLatitudeAndLongitude(BigDecimal.ONE, BigDecimal.TEN));
        assertEquals("Tracking não encontrado", exception.getMessage());
    }

    @Test
    void deveCalcularTempoEstimadoDeEntrega() {
        Order order = new Order(1L, LocalDateTime.now(), "12345-678", deliveryPerson.getId());
        LocalDateTime estimatedTime = trackingService.calculateEstimatedDelivery(order);

        assertNotNull(estimatedTime);
        assertTrue(estimatedTime.isAfter(LocalDateTime.now()));
    }
}