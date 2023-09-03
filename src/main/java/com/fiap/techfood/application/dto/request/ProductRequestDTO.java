package com.fiap.techfood.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
  private String name;
  private BigDecimal price;
  private Long categoryId;
  private String description;
}
