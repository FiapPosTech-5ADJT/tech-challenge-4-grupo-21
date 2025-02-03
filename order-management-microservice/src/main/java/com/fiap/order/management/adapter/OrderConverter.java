package com.fiap.order.management.adapter;

            import com.fiap.order.management.domain.ItemDomain;
            import com.fiap.order.management.domain.OrderDomain;
            import com.fiap.order.management.dto.ItemDTO;
            import com.fiap.order.management.dto.OrderDTO;

            import java.util.List;
            import java.util.stream.Collectors;

            public class OrderConverter {

                public static OrderDomain toDomain(OrderDTO orderDTO) {
                    List<ItemDomain> itemDomains = orderDTO.getItems().stream()
                            .map(OrderConverter::toDomain)
                            .collect(Collectors.toList());

                    return new OrderDomain(orderDTO.getCustomerId(), itemDomains);
                }

                public static OrderDTO toDTO(OrderDomain orderDomain) {
                    List<ItemDTO> itemDTOs = orderDomain.getItems().stream()
                            .map(OrderConverter::toDTO)
                            .collect(Collectors.toList());

                    return new OrderDTO(orderDomain.getCustomerId(), itemDTOs, orderDomain.getStatus());
                }

                private static ItemDomain toDomain(ItemDTO itemDTO) {
                    return new ItemDomain(null, itemDTO.getProductId(), itemDTO.getQuantity());
                }

                private static ItemDTO toDTO(ItemDomain itemDomain) {
                    return new ItemDTO(itemDomain.getProductId(), itemDomain.getQuantity());
                }
            }
