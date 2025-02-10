package br.com.fiap.logistic.service.impl;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.domain.DeliveryPerson;
import br.com.fiap.logistic.domain.Order;
import br.com.fiap.logistic.entity.DeliveryPersonEntity;
import br.com.fiap.logistic.gateway.DeliveryPersonGateway;
import br.com.fiap.logistic.service.DeliveryPersonService;
import br.com.fiap.logistic.service.OrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeliveryPersonServiceImpl implements DeliveryPersonService {

    private final DeliveryPersonGateway deliveryPersonGateway;
    private final OrderService orderService;
    private final DeliveryPersonConverter deliveryPersonConverter;

    @Override
    public DeliveryPerson createDeliveryPerson(DeliveryPerson deliveryPerson) {
        DeliveryPersonEntity deliveryPersonEntity = deliveryPersonConverter.convertToEntity(deliveryPerson);
        return deliveryPersonConverter.convertToDomain(deliveryPersonGateway.save(deliveryPersonEntity));
    }

    @Override
    public void assignOrderToDeliveryPerson(Long deliveryPersonId, Long orderId) {
        DeliveryPersonEntity deliveryPersonEntity = deliveryPersonGateway.findById(deliveryPersonId)
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));

        DeliveryPerson deliveryPerson = deliveryPersonConverter.convertToDomain(deliveryPersonEntity);

        final Order order = orderService.getOrderById(orderId);

        deliveryPerson.assignOrder(order);

        deliveryPersonGateway.save(deliveryPersonConverter.convertToEntity(deliveryPerson));
    }

    @Override
    public void completeDelivery(Long deliveryPersonId, Long orderId) {
        DeliveryPersonEntity deliveryPersonEntity = deliveryPersonGateway.findById(deliveryPersonId)
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));
        DeliveryPerson deliveryPerson = deliveryPersonConverter.convertToDomain(deliveryPersonEntity);
        final Order order = orderService.getOrderById(orderId);
        deliveryPerson.completeOrder(order);

        deliveryPersonGateway.save(deliveryPersonConverter.convertToEntity(deliveryPerson));
    }
}
