package br.com.fiap.productcatalog.infraestructure.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCSVDTO {
    String name;
    String description;
    BigDecimal price;
    String categoryName;
    BigDecimal quantity;
}
