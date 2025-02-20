package br.com.fiap.customermicroservice.application.dto;

public record CustomerDto(
        long l, String name,
        String email,
        String phoneNumber,
        Long cep,
        String cnpj
) {}
