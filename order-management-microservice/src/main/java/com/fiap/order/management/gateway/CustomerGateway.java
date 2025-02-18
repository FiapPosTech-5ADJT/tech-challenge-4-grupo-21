package com.fiap.order.management.gateway;

import com.fiap.order.management.dto.CustomerDTO;

public interface CustomerGateway {
    CustomerDTO findById(Long customerId);
}
