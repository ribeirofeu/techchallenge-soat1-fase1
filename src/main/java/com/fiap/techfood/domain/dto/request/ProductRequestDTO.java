package com.fiap.techfood.domain.dto.request;

import com.fiap.techfood.domain.Category;
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
  private Category category;
  private String description;
}
