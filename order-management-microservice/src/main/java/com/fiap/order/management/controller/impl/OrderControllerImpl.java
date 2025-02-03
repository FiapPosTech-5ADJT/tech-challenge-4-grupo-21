package com.fiap.order.management.controller.impl;

    import com.fiap.order.management.adapter.OrderConverter;
    import com.fiap.order.management.domain.OrderDomain;
    import com.fiap.order.management.dto.OrderDTO;
    import com.fiap.order.management.usecase.CreateOrderUseCase;
    import com.fiap.order.management.usecase.FindOrderByIdUseCase;
    import com.fiap.order.management.controller.OrderController;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/orders")
    @RequiredArgsConstructor
    public class OrderControllerImpl implements OrderController {
        private final CreateOrderUseCase createOrderUseCase;
        private final FindOrderByIdUseCase findOrderByIdUseCase;

        @PostMapping()
        @Override
        public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO) {
            OrderDomain orderDomain = OrderConverter.toDomain(orderDTO);
          OrderDomain createdOrder = createOrderUseCase.execute(orderDomain);
          OrderDTO createdOrderDTO = OrderConverter.toDTO(createdOrder);
            return ResponseEntity.ok().body(createdOrderDTO);
        }

        @GetMapping("/{orderId}")
        @Override
        public ResponseEntity<OrderDTO> findById(@PathVariable Long orderId) {
          OrderDomain findedOrder = findOrderByIdUseCase.execute(orderId);
          OrderDTO findedOrderDto = OrderConverter.toDTO(findedOrder);
            return ResponseEntity.ok(findedOrderDto);
        }
    }
