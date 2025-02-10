package com.fiap.order.management.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "products-client", url = "${stock-products.url}")
public interface ProductClient {
  
    @GetMapping(value = "/{productId}/stock")
    ResponseEntity<BigDecimal>  findById(@PathVariable(value = "productId") Long productId);

}
