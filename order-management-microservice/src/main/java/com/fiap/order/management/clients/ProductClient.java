package com.fiap.order.management.clients;

import com.fiap.order.management.dto.ProductFindByIdResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-client", url = "${stock-products.url}/products")
public interface ProductClient {
  
    @GetMapping(value = "/{productId}")
    ProductFindByIdResponseDTO findById(@PathVariable(value = "productId") String productId);

}
