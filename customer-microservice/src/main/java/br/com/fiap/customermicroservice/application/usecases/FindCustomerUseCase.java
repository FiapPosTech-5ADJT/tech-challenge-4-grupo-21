package br.com.fiap.customermicroservice.application.usecases;

import br.com.fiap.customermicroservice.domain.entity.Customer;
import br.com.fiap.customermicroservice.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCustomerUseCase {

    @Autowired
    private CustomerService customerService;

    public List<Customer> findAll() {
        return customerService.findAll();
    }

    public Customer findById(Long id) {
        return customerService.findById(id);
    }

    public Customer findByCnpj(String cnpj) {
        return customerService.findByCnpj(cnpj);
    }
}
