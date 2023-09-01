package com.fiap.techfood.application.interfaces.gateway;

import com.fiap.techfood.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(Long id);

    void save(Product product);

    List<Product> findAll();

    List<Product> findAllByIdIn(List<Long> productIds);

    void deleteProduct(Long id);

    void updateProduct(Product product);

}
