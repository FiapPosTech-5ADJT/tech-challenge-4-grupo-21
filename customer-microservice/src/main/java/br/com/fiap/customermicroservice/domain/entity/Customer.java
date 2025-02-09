package br.com.fiap.customermicroservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Long cep;
    private String cnpj;
    private LocalDateTime creationDate;

    public Customer() {}

    public Customer(Long id, String name, String email, String phoneNumber, Long cep, String cnpj, LocalDateTime creationDate) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cep = cep;
        this.cnpj = cnpj;
        this.creationDate = creationDate;
    }

    public Customer(Object o, String name, String email, String s, Long cep, LocalDateTime now) {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCep() {
        return cep;
    }

    public void setCep(final Long cep) {
        this.cep = cep;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
