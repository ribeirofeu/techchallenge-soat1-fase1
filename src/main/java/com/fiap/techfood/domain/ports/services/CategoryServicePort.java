package com.fiap.techfood.domain.ports.services;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.request.CategoryRequestDTO;

import java.util.List;

public interface CategoryServicePort {

    Long createCategory(CategoryRequestDTO dto) ;

    Category findCategoryById(Long id);

    List<Product> findProductByCategory(Long id);

    List<Category> findAllCategories();

    void deleteCategory(Long id);

    void updateCategory(Long id, CategoryRequestDTO dto);

}
