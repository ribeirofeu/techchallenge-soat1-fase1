package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Product")
@NoArgsConstructor
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  private Long id;

  private String name;
  private BigDecimal unitValue;
  private String description;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private CategoryEntity category;

  public ProductEntity (Product product) {
      this.name = product.getName();
      this.unitValue = product.getUnitValue();
      this.description = product.getDescription();
      this.category = new CategoryEntity(product.getCategory());
  }

    public Product toProduct() {
        return Product.builder()
                .id(id)
                .name(name)
                .unitValue(unitValue)
                .description(description)
                .category(category.toCategory())
                .build();
    }

}
