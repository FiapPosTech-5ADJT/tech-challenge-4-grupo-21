package com.fiap.order.management.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerDTOTest {

    @Test
    void shouldCreateCustomerDTOWithValidData() {
        Long id = 1L;
        String name = "John Doe";
        String uf = "SP";
        String address = "123 Main St";

        CustomerDTO customerDTO = new CustomerDTO(id, name, uf, address);

        assertThat(customerDTO.getId()).isEqualTo(id);
        assertThat(customerDTO.getName()).isEqualTo(name);
        assertThat(customerDTO.getUf()).isEqualTo(uf);
        assertThat(customerDTO.getAddress()).isEqualTo(address);
    }

    @Test
    void shouldSetAndGetFields() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John Doe", "SP", "123 Main St");

        customerDTO.setId(2L);
        customerDTO.setName("Jane Doe");
        customerDTO.setUf("RJ");
        customerDTO.setAddress("456 Elm St");

        assertThat(customerDTO.getId()).isEqualTo(2L);
        assertThat(customerDTO.getName()).isEqualTo("Jane Doe");
        assertThat(customerDTO.getUf()).isEqualTo("RJ");
        assertThat(customerDTO.getAddress()).isEqualTo("456 Elm St");
    }
}
