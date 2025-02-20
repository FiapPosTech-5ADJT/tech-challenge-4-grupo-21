package br.com.fiap.customermicroservice.infraestructure.api;

import br.com.fiap.customermicroservice.application.dto.CustomerDto;
import br.com.fiap.customermicroservice.application.usecases.CreateCustomerUseCase;
import br.com.fiap.customermicroservice.application.usecases.FindCustomerUseCase;
import br.com.fiap.customermicroservice.domain.entity.Customer;
import br.com.fiap.customermicroservice.domain.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private FindCustomerUseCase findCustomerUseCase;

    @Mock
    private CreateCustomerUseCase createCustomerUseCase;

    @Mock
    private CustomerService customerService;

    private Customer customer;
    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Empresa X");
        customer.setCnpj("12345678000199");
        customer.setEmail("empresa@example.com");

        customerDto = new CustomerDto(1L, "Empresa X", "empresa@example.com", "11999999999", 12345678L, "12345678000199");
    }

    @Test
    void shouldReturnAllCustomers() {
        when(findCustomerUseCase.findAll()).thenReturn(List.of(customer));

        List<Customer> customers = customerController.findAll();

        assertFalse(customers.isEmpty());
        assertEquals(1, customers.size());
        assertEquals("Empresa X", customers.get(0).getName());
    }

    @Test
    void shouldReturnCustomerById() {
        when(findCustomerUseCase.findById(1L)).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.findById(1L);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Empresa X", response.getBody().getName());
    }

    @Test
    void shouldSaveCustomer() {
        when(createCustomerUseCase.save(customerDto)).thenReturn(ResponseEntity.ok(customer));

        ResponseEntity<Customer> response = customerController.save(customerDto);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Empresa X", response.getBody().getName());
    }
}
