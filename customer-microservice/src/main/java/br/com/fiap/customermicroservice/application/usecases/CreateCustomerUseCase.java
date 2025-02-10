package br.com.fiap.customermicroservice.application.usecases;

import br.com.fiap.customermicroservice.application.dto.CustomerDto;
import br.com.fiap.customermicroservice.domain.entity.Customer;
import br.com.fiap.customermicroservice.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateCustomerUseCase {

    @Autowired
    private CustomerService customerService;

    public ResponseEntity<Customer> save(CustomerDto customerDto) {
        Customer customer = new Customer(null, customerDto.name(), customerDto.email(), customerDto.phoneNumber(), customerDto.cep(), customerDto.cnpj(), LocalDateTime.now());
        Customer savedCustomer = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }
}