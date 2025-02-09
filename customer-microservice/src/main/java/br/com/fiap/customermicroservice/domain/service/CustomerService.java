package br.com.fiap.customermicroservice.domain.service;

import br.com.fiap.customermicroservice.domain.entity.Customer;
import br.com.fiap.customermicroservice.domain.exception.CustomerNotFoundException;
import br.com.fiap.customermicroservice.infraestructure.persistence.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Cliente não Encontrado!"));
    }

    public Customer findByCnpj(String cnpj) {
        return customerRepository.findByCnpj(cnpj).orElseThrow(() -> new CustomerNotFoundException("Cliente não Encontrado!"));
    }
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Cliente não encontrado!");
        }
        customerRepository.deleteById(id);
    }


}
