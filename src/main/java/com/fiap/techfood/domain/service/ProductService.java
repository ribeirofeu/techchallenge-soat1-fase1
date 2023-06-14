package com.fiap.techfood.domain.service;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.ProductDTO;
import com.fiap.techfood.domain.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    UUID createProduct(ProductDTO dto) ;

    Product findProductById(UUID id);

    List<Product> findProductByCategory(Category category);

    List<Product> findAllProducts();

}
