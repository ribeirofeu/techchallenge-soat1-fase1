package com.fiap.techfood.domain.service.impl;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.ProductDTO;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.repository.ProductRepository;
import com.fiap.techfood.domain.service.ProductService;

import java.util.List;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

  private final ProductRepository repo;

  public ProductServiceImpl(final ProductRepository repo) {
    this.repo = repo;
  }

  @Override
  public UUID createProduct(ProductDTO dto) {
    Product product = Product.fromProductDTO(dto);
    repo.save(product);
    return product.getId();
  }

  @Override
  public Product findProductById(UUID id) {
    return repo.findById(id).orElseThrow(() -> new BusinessException("Product ID not found"));
  }

  @Override
  public List<Product> findProductByCategory(Category category) {
    return repo.findByCategory(category)
        .orElseThrow(() -> new BusinessException("Not registered product in this category"));
  }

  @Override
  public List<Product> findAllProducts() {
    return repo.findAll();
  }
}
