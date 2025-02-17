package br.com.fiap.logistic.service.impl;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.OrderStatus;
import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import br.com.fiap.logistic.exception.EntregadorNaoDisponivelEncontradoException;
import br.com.fiap.logistic.exception.PedidoNaoEncontradoException;
import br.com.fiap.logistic.gateway.DeliveryPersonGateway;
import br.com.fiap.logistic.service.DeliveryPersonService;
import br.com.fiap.logistic.service.OrderService;
import br.com.fiap.logistic.service.TrackingService;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class DeliveryPersonServiceImpl implements DeliveryPersonService {

    private static final int TOTAL_ORDERS_LIMIT = 5;

    private final DeliveryPersonGateway deliveryPersonGateway;
    private final OrderService orderService;
    private final DeliveryPersonConverter deliveryPersonConverter;
    private final TrackingService trackingService;

    @Override
    public DeliveryPerson createDeliveryPerson(DeliveryPerson deliveryPerson) {
        DeliveryPersonEntity deliveryPersonEntity = deliveryPersonConverter.convertToEntity(deliveryPerson);
        return deliveryPersonConverter.convertToDomain(deliveryPersonGateway.save(deliveryPersonEntity));
    }

    @Override
    public Tracking assignOrderToDeliveryPerson(Long deliveryPersonId,
                                            Long orderId,
                                            String zipCode) {
        DeliveryPersonEntity deliveryPersonEntity = deliveryPersonGateway.findById(deliveryPersonId)
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));

        DeliveryPerson deliveryPerson = deliveryPersonConverter.convertToDomain(deliveryPersonEntity);

        int totalOrders = getTotalOrdersByDeliveryPerson(deliveryPersonId);

        if(!deliveryPerson.isAvailable() || totalOrders > TOTAL_ORDERS_LIMIT) {
            throw new EntregadorNaoDisponivelEncontradoException("Entregador não disponível");
        }

        deliveryPerson.setAvailable(totalOrders != TOTAL_ORDERS_LIMIT);

        final Order order = orderService.getOrderById(orderId)
                .orElseGet(() -> createServiceOrder(orderId, zipCode, deliveryPerson));

        Tracking tracking = createTracking(deliveryPerson, order);

        deliveryPersonGateway.save(deliveryPersonConverter.convertToEntity(deliveryPerson));

        return tracking;
    }

    private Tracking createTracking(DeliveryPerson deliveryPerson, Order order) {
        LocalDateTime estimatedDeliveryTime = trackingService.calculateEstimatedDelivery(order);
        Tracking tracking = new Tracking(
                order.getId(),
                deliveryPerson.getId(),
                estimatedDeliveryTime
        );
        return trackingService.createTracking(tracking);
    }

    private Order createServiceOrder(Long orderId, String zipCode, DeliveryPerson deliveryPerson) {
        Order order = new Order(orderId, getEstimatedDeliveryTime(), zipCode, deliveryPerson.getId());
        return orderService.createOrder(order);
    }

    @Override
    public void completeDelivery(Long orderId) {
        final Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));
        final DeliveryPersonEntity deliveryPersonEntity = deliveryPersonGateway.findById(order.getDeliveryPersonId())
                .orElseThrow(() -> new EntregadorNaoDisponivelEncontradoException("Entregador não encontrado"));
        DeliveryPerson deliveryPerson = deliveryPersonConverter.convertToDomain(deliveryPersonEntity);
        orderService.updateOrderStatus(order.getOrderExternalId(), OrderStatus.COMPLETED);
        deliveryPersonGateway.save(deliveryPersonConverter.convertToEntity(deliveryPerson));
    }

    private LocalDateTime getEstimatedDeliveryTime() {
        return LocalDateTime.now().plusDays(2);
    }

    private int getTotalOrdersByDeliveryPerson(Long deliveryPersonId) {
        return orderService.getOrdersByDeliveryPerson(deliveryPersonId).size();
    }
}
