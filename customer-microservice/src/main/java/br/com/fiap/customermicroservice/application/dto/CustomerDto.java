package br.com.fiap.customermicroservice.application.dto;

import java.time.LocalDateTime;

public record CustomerDto(
        String name,
        String email,
        String phoneNumber,
        Long cep,
        String cnpj
) {}
