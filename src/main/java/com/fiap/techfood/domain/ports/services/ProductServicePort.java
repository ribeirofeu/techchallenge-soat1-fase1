package com.fiap.techfood.domain.ports.services;

import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.request.ProductRequestDTO;

import java.util.List;

public interface ProductServicePort {

    Long createProduct(ProductRequestDTO dto) ;

    Product findProductById(Long id);

    List<Product> findAllProducts();

    void deleteProduct(Long id);

    void updateProduct(Long id, ProductRequestDTO dto);

}
