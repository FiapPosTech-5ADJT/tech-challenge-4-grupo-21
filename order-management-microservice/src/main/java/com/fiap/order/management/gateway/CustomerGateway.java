package com.fiap.order.management.gateway;

import com.fiap.order.management.dto.CustomerDTO;
import org.springframework.web.bind.annotation.PathVariable;

public interface CustomerGateway {
    CustomerDTO findById(Long customerId);
}
