package com.fiap.techfood.domain.repository;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {

    Optional<Category> findById(Long id);

    void save(Category category);

    List<Category> findAll();

    List<Product> findProductByCategory(Long id);

    void deleteCategory(Long id);

    void updateCategory(Category category);

}
