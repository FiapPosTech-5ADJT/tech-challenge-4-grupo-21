package com.fiap.order.management.gateway;

  import com.fiap.order.management.clients.CustomerClient;
  import com.fiap.order.management.dto.CustomerDTO;
  import com.fiap.order.management.gateway.api.CustomerGatewayImpl;
  import org.junit.jupiter.api.BeforeEach;
  import org.junit.jupiter.api.Test;
  import org.mockito.Mockito;

  import static org.assertj.core.api.Assertions.assertThat;
  import static org.mockito.Mockito.when;

  class CustomerGatewayImplTest {

      private CustomerClient customerClient;
      private CustomerGateway customerGateway;

      @BeforeEach
      void setUp() {
          customerClient = Mockito.mock(CustomerClient.class);
          customerGateway = new CustomerGatewayImpl(customerClient);
      }

      @Test
      void shouldFindCustomerById() {
          Long customerId = 1L;
          CustomerDTO expectedCustomer = new CustomerDTO(customerId, "Mock Customer", "SP", "123 Mock Street");
          when(customerClient.findById(customerId)).thenReturn(expectedCustomer);

          CustomerDTO actualCustomer = customerGateway.findById(customerId);

          assertThat(actualCustomer).isEqualTo(expectedCustomer);
      }
  }
