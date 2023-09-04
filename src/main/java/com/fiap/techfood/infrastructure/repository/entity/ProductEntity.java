package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.products.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Long id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private BigDecimal price;
  private String description;
  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private CategoryEntity category;

  public ProductEntity (Product product) {
      this.id = product.getId();
      this.name = product.getName();
      this.price = product.getPrice();
      this.description = product.getDescription();
      this.category = new CategoryEntity(product.getCategory());
  }

  public Product toProduct() {
    return Product.builder()
            .id(id)
            .name(name)
            .price(price)
            .description(description)
            .category(category.toCategory())
            .build();
  }

  public static ProductEntity from(Product product) {
      return new ProductEntity(product);
  }
}
