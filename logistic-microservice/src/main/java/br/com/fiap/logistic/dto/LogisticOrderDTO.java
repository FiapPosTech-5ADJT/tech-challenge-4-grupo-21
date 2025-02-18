package br.com.fiap.logistic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticOrderDTO {
    private Long orderId;
    private Long customerId;
    private List<ItemDTO> items;
}
