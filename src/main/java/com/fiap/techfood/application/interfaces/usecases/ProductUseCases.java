package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.domain.Product;
import com.fiap.techfood.application.dto.request.ProductRequestDTO;

import java.util.List;

public interface ProductServicePort {

    Long createProduct(ProductRequestDTO dto) ;

    Product findProductById(Long id);

    List<Product> findAllProducts();

    void deleteProduct(Long id);

    void updateProduct(Long id, ProductRequestDTO dto);

}
