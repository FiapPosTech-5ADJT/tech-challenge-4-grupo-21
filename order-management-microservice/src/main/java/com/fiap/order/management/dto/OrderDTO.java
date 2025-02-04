package com.fiap.order.management.dto;

import com.fiap.order.management.domain.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDTO {
  private Long customerId;
  private List<ItemDTO> items;
}
