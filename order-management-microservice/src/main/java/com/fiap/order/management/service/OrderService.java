package com.fiap.order.management.service;

import com.fiap.order.management.domain.OrderDomain;

public interface OrderService {
  OrderDomain create(OrderDomain order);

  OrderDomain findById(Long id);
}
