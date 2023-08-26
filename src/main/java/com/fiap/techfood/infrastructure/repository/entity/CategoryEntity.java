package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.entities.Category;
import com.fiap.techfood.domain.entities.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "category")
@NoArgsConstructor
public class CategoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Long id;
  @Column(nullable = false)
  private String name;
  @OneToMany(mappedBy = "category")
  private List<ProductEntity> products;

  public CategoryEntity(Category category) {
    this.id = category.getId();
    this.name = category.getName();
  }

  public Category toCategory() {
    Category category = new Category();
    category.setId(id);
    category.setName(name);
    return category;
  }

  public List<Product> getProducts() {
    return products.stream().map(ProductEntity::toProduct).collect(Collectors.toList());
  }
}
