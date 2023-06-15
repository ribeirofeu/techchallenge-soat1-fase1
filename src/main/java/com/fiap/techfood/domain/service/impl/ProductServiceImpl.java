package com.fiap.techfood.domain.service.impl;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.ProductDTO;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.repository.ProductRepository;
import com.fiap.techfood.domain.service.ProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

  private final ProductRepository repo;

  public ProductServiceImpl(final ProductRepository repo) {
    this.repo = repo;
  }

  @Override
  public Long createProduct(ProductDTO dto) {
    Product product = Product.fromProductDTO(dto);
    repo.save(product);
    return product.getId();
  }

  @Override
  public Product findProductById(Long id) {
    return repo.findById(id)
        .orElseThrow(() -> new BusinessException("Product ID not found", HttpStatus.NOT_FOUND));
  }

  @Override
  public List<Product> findAllProducts() {
    return repo.findAll();
  }

    @Override
    public void deleteProduct(Long id) {
        repo.deleteProduct(id);
    }

    @Override
    public void updateProduct(Long id, ProductDTO dto) {
          Product product = Product.fromProductDTO(dto);
          product.setId(id);
          repo.updateProduct(product);
    }
}