package com.fiap.techfood.domain.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

  private Long id;
  private String name;
  private List<Product> products;


  public static Category fromCategoryDto(CategoryRequestDTO dto) {
    return Category.builder().name(dto.getName()).build();
  }
}
