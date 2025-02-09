package br.com.fiap.customermicroservice.infraestructure.persistence;

import br.com.fiap.customermicroservice.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCnpj(String cnpj);
}
