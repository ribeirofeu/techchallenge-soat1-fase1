package com.fiap.techfood.domain.interfaces.usecases;

import com.fiap.techfood.domain.dto.request.ProductRequestDTO;
import com.fiap.techfood.domain.entities.Product;

import java.util.List;

public interface ProductUseCases {
    Long createProduct(ProductRequestDTO dto);

    Product findProductById(Long id);

    List<Product> findAllProducts();

    void deleteProduct(Long id);

    void updateProduct(Long id, ProductRequestDTO dto);
}
