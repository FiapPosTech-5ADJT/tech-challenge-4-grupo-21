package br.com.fiap.customermicroservice.infraestructure.api;

import br.com.fiap.customermicroservice.application.dto.CustomerDto;
import br.com.fiap.customermicroservice.application.usecases.CreateCustomerUseCase;
import br.com.fiap.customermicroservice.application.usecases.DeleteCustomerUseCase;
import br.com.fiap.customermicroservice.application.usecases.FindCustomerUseCase;
import br.com.fiap.customermicroservice.domain.entity.Customer;
import br.com.fiap.customermicroservice.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final FindCustomerUseCase findCustomerUseCase;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(FindCustomerUseCase findCustomerUseCase, CreateCustomerUseCase createCustomerUseCase, CustomerService customerService) {
        this.findCustomerUseCase = findCustomerUseCase;
        this.createCustomerUseCase = createCustomerUseCase;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll() {
        return findCustomerUseCase.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(Long id) {
        Customer customer = findCustomerUseCase.findById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody CustomerDto customerDto) {
        return createCustomerUseCase.save(customerDto);
    }
}