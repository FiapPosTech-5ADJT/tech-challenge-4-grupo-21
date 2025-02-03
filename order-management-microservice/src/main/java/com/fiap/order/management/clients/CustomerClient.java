package com.fiap.order.management.clients;

import com.fiap.order.management.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-client", url = "${customer-management.url}/customers")
public interface CustomerClient {

    @GetMapping(value = "/{customerId}")
    CustomerDTO findById(@PathVariable(value = "customerId") Long customerId);

}
