package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "category")
@NoArgsConstructor
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  private Long id;

  private String name;

  @OneToMany(mappedBy = "category")
  private List<ProductEntity> products;

  public CategoryEntity(Category category) {
    this.id = category.getId();
    this.name = category.getName();
  }

  public Category toCategory() {
    return Category.builder().id(id).name(name).build();
  }

  public List<Product> getProducts() {
    return products.stream().map(ProductEntity::toProduct).collect(Collectors.toList());
  }
}
