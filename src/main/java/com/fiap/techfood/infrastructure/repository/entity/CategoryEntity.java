package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.Category;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "category")
@NoArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    public CategoryEntity(Category category) {
        this.name = category.getName();
    }
}
