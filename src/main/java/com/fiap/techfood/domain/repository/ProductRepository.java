package com.fiap.techfood.domain.repository;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Optional<Product> findById(Long id);

    void save(Product product);

    List<Product> findAll();

    void deleteProduct(Long id);

    void updateProduct(Product product);

}
