package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.application.dto.request.CategoryRequestDTO;

import java.util.List;

public interface CategoryUseCases {

    Long createCategory(CategoryRequestDTO dto) ;

    Category findCategoryById(Long id);

    List<Product> findProductByCategory(Long id);

    List<Category> findAllCategories();

    void deleteCategory(Long id);

    void updateCategory(Long id, CategoryRequestDTO dto);

}
