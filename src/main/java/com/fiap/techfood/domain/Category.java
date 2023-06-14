package com.fiap.techfood.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.techfood.domain.dto.CategoryDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

  private Long id;
  private String name;
  private List<Product> products;


  public static Category fromCategoryDto(CategoryDTO dto) {
    return Category.builder().name(dto.getName()).build();
  }
}
