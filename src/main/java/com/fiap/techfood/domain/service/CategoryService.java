package com.fiap.techfood.domain.service;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.CategoryDTO;
import com.fiap.techfood.domain.dto.ProductDTO;
import java.util.List;

public interface CategoryService {

    Long createCategory(CategoryDTO dto) ;

    Category findCategoryById(Long id);

    List<Product> findProductByCategory(Long id);

    List<Category> findAllCategories();

}
