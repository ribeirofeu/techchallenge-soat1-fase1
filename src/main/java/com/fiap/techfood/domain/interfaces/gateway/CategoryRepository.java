package com.fiap.techfood.domain.interfaces.gateway;

import com.fiap.techfood.domain.entities.Category;
import com.fiap.techfood.domain.entities.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(Long id);

    void save(Category category);

    List<Category> findAll();

    List<Product> findProductByCategory(Long id);

    void deleteCategory(Long id);

    void updateCategory(Category category);

}
