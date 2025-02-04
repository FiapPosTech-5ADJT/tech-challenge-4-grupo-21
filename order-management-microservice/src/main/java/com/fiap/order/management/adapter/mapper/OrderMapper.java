package com.fiap.order.management.adapter.mapper;

import com.fiap.order.management.domain.ItemDomain;
import com.fiap.order.management.domain.OrderDomain;
import com.fiap.order.management.dto.ItemDTO;
import com.fiap.order.management.dto.LogisticOrderDTO;
import com.fiap.order.management.entity.Item;
import com.fiap.order.management.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toModel(OrderDomain orderDomain) {
        List<Item> items = orderDomain.getItems().stream()
                .map(OrderMapper::toModel)
                .collect(Collectors.toList());

        return new Order(null, orderDomain.getCustomerId(),orderDomain.getStatus(), items);
    }

    public static OrderDomain toDomain(Order order) {
        List<ItemDomain> itemDomains = order.getItems().stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());

        return new OrderDomain(order.getCustomerId(), itemDomains);
    }

    private static Item toModel(ItemDomain itemDomain) {
        return new Item(itemDomain.getId(), itemDomain.getProductId(), itemDomain.getQuantity());
    }

    private static ItemDomain toDomain(Item item) {
        return new ItemDomain(item.getId(), item.getProductId(), item.getQuantity());
    }

    public static LogisticOrderDTO toLogisticOrderDTO(Order order) {
        List<ItemDTO> items = order.getItems().stream()
                .map(item -> new ItemDTO(item.getProductId(), item.getQuantity()))
                .collect(Collectors.toList());

        return new LogisticOrderDTO(
                order.getId(),
                order.getCustomerId(),
                items
        );
    }
}
