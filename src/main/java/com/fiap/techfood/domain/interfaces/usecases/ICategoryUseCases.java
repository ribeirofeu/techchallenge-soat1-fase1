package com.fiap.techfood.domain.interfaces.usecases;

import com.fiap.techfood.domain.dto.request.CategoryRequestDTO;
import com.fiap.techfood.domain.entities.Category;
import com.fiap.techfood.domain.entities.Product;

import java.util.List;

public interface ICategoryUseCases {
    Long createCategory(CategoryRequestDTO dto);

    Category findCategoryById(Long id);

    List<Product> findProductByCategory(Long id);

    List<Category> findAllCategories();

    void deleteCategory(Long id);

    void updateCategory(Long id, CategoryRequestDTO dto);
}
