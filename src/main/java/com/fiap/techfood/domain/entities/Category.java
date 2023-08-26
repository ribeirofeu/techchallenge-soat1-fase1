package com.fiap.techfood.domain.entities;

import com.fiap.techfood.domain.dto.request.CategoryRequestDTO;

import java.util.List;

public class Category {

    private Long id;
    private String name;
    private List<Product> products;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static Category fromCategoryDto(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
