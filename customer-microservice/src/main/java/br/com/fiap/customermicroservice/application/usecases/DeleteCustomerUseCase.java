package br.com.fiap.customermicroservice.application.usecases;

import br.com.fiap.customermicroservice.application.dto.CustomerDto;
import br.com.fiap.customermicroservice.domain.entity.Customer;
import br.com.fiap.customermicroservice.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerUseCase {

    @Autowired
    private CustomerService customerService;

    public void delete(Long id) {
    }
}
