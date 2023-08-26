package com.fiap.techfood.domain.dto.request;

import java.math.BigDecimal;

public class ProductRequestDTO {
  private String name;
  private BigDecimal price;
  private Long categoryId;
  private String description;

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public String getDescription() {
    return description;
  }
}
