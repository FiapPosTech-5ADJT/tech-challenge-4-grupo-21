package com.fiap.order.management.gateway;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface ProductGateway {
  ResponseEntity<BigDecimal> findById(Long productId);
}
