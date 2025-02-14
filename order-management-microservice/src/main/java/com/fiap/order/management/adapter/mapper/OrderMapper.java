package com.fiap.order.management.adapter.mapper;

import com.fiap.order.management.domain.ItemDomain;
import com.fiap.order.management.domain.OrderDomain;
import com.fiap.order.management.dto.ItemDTO;
import com.fiap.order.management.dto.LogisticOrderDTO;
import com.fiap.order.management.entity.Item;
import com.fiap.order.management.entity.Order;

import java.util.List;

public class OrderMapper {

    // Private constructor to hide the implicit public one
    private OrderMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Order toModel(OrderDomain orderDomain) {
        List<Item> items = orderDomain.getItems().stream()
                .map(OrderMapper::toModel)
                .toList(); // Replaced Collectors.toList() with toList()

        return new Order(null, orderDomain.getCustomerId(), orderDomain.getStatus(), items);
    }

    public static OrderDomain toDomain(Order order) {
        List<ItemDomain> itemDomains = order.getItems().stream()
                .map(OrderMapper::toDomain)
                .toList(); // Replaced Collectors.toList() with toList()

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
                .toList(); // Replaced Collectors.toList() with toList()

        return new LogisticOrderDTO(
                order.getId(),
                order.getCustomerId(),
                items
        );
    }
}
