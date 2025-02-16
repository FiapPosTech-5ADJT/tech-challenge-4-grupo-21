package br.com.fiap.logistic.dto;

import java.time.LocalDateTime;

public record OrderDTO(Long orderExternalId,
                       LocalDateTime estimatedDelivery,
                       LocalDateTime deliveredAt,
                       String zipCode,
                       String status) {
}
