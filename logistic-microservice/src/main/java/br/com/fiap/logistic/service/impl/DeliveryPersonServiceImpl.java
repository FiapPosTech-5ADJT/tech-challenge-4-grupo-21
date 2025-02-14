package br.com.fiap.logistic.service.impl;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.domain.OrderStatus;
import br.com.fiap.logistic.domain.Tracking;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import br.com.fiap.logistic.gateway.DeliveryPersonGateway;
import br.com.fiap.logistic.service.DeliveryPersonService;
import br.com.fiap.logistic.service.OrderService;
import br.com.fiap.logistic.service.TrackingService;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class DeliveryPersonServiceImpl implements DeliveryPersonService {

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
    public void assignOrderToDeliveryPerson(Long deliveryPersonId,
                                            Long orderId,
                                            Long zipCode) {
        DeliveryPersonEntity deliveryPersonEntity = deliveryPersonGateway.findById(deliveryPersonId)
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));

        DeliveryPerson deliveryPerson = deliveryPersonConverter.convertToDomain(deliveryPersonEntity);

        if(!deliveryPerson.isAvailable()){
            throw new RuntimeException("Entregador não disponível");
        }

        final Order order = orderService.getOrderById(orderId)
                .orElseGet(() -> createServiceOrder(orderId, zipCode));

        createTracking(orderId, deliveryPerson, order);


        deliveryPerson.assignOrder(order);

        deliveryPersonGateway.save(deliveryPersonConverter.convertToEntity(deliveryPerson));
    }

    private void createTracking(Long orderId, DeliveryPerson deliveryPerson, Order order) {
        Tracking tracking = new Tracking(
                orderId,
                deliveryPerson.getId(),
                order.getZipCode()
        )
        trackingService.createTracking(tracking);
    }

    private Order createServiceOrder(Long orderId, Long zipCode) {
        Order order = new Order(orderId, getEstimatedDeliveryTime(), zipCode);
        return orderService.createOrder(order);
    }

    @Override
    public void completeDelivery(Long orderId) {
        final Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        final DeliveryPersonEntity deliveryPersonEntity = deliveryPersonGateway.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));
        DeliveryPerson deliveryPerson = deliveryPersonConverter.convertToDomain(deliveryPersonEntity);
        deliveryPerson.completeOrder(order);
        orderService.updateOrderStatus(order.getId(), OrderStatus.COMPLETED);
        deliveryPersonGateway.save(deliveryPersonConverter.convertToEntity(deliveryPerson));
    }

    private LocalDateTime getEstimatedDeliveryTime() {
        return LocalDateTime.now().plusDays(2);
    }
}
