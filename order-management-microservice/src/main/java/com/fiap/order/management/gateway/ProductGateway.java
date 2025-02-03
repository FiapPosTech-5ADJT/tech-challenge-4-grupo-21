package com.fiap.order.management.gateway;

import com.fiap.order.management.dto.ProductFindByIdResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductGateway {
  ProductFindByIdResponseDTO findById(String productId);
}
