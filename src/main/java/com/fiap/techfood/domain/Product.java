package com.fiap.techfood.domain;

import com.fiap.techfood.domain.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class Product {

  private UUID id;
  private String name;
  private BigDecimal unitValue;
  private Category category;
  private String description;

  public static Product fromProductDTO(ProductDTO dto) {
    return Product.builder()
        .name(dto.getName())
        .unitValue(dto.getUnitValue())
        .category(dto.getCategory())
        .description(dto.getDescription())
        .build();
  }
}
