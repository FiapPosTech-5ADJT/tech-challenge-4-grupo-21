package com.fiap.order.management.gateway.api;

import com.fiap.order.management.clients.CustomerClient;
import com.fiap.order.management.dto.CustomerDTO;
import com.fiap.order.management.gateway.CustomerGateway;

public class CustomerGatewayImpl implements CustomerGateway {

  private final CustomerClient customerClient;

  public CustomerGatewayImpl(CustomerClient customerClient) {
    this.customerClient = customerClient;
  }

  @Override
    public CustomerDTO findById(Long customerId) {
         // return customerClient.findById(customerId);
          return new CustomerDTO(
            customerId,
            "Mock Customer",
            "SP",
            "123 Mock Street"
          );
    }
}
