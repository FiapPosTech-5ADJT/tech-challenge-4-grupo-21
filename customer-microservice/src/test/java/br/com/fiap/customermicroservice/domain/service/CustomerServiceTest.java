package br.com.fiap.customermicroservice.domain.service;

import br.com.fiap.customermicroservice.domain.entity.Customer;
import br.com.fiap.customermicroservice.domain.exception.CustomerNotFoundException;
import br.com.fiap.customermicroservice.infraestructure.persistence.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Empresa X");
        customer.setCnpj("12345678000199");
        customer.setEmail("empresa@example.com");
    }

    @Test
    void shouldReturnAllCustomers() {
        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<Customer> customers = customerService.findAll();

        assertFalse(customers.isEmpty());
        assertEquals(1, customers.size());
        assertEquals("Empresa X", customers.get(0).getName());
    }

    @Test
    void shouldReturnCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.findById(1L);

        assertNotNull(foundCustomer);
        assertEquals("Empresa X", foundCustomer.getName());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFoundById() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.findById(99L));
    }

    @Test
    void shouldReturnCustomerByCnpj() {
        when(customerRepository.findByCnpj("12345678000199")).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.findByCnpj("12345678000199");

        assertNotNull(foundCustomer);
        assertEquals("Empresa X", foundCustomer.getName());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFoundByCnpj() {
        when(customerRepository.findByCnpj("00000000000000")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.findByCnpj("00000000000000"));
    }

    @Test
    void shouldSaveCustomer() {
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer savedCustomer = customerService.save(customer);

        assertNotNull(savedCustomer);
        assertEquals("Empresa X", savedCustomer.getName());
    }

    @Test
    void shouldDeleteCustomerIfExists() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(1L);

        assertDoesNotThrow(() -> customerService.delete(1L));

        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingCustomer() {
        when(customerRepository.existsById(99L)).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> customerService.delete(99L));
    }
}
