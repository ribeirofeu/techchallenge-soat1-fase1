package com.fiap.techfood.domain.services;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.request.ProductRequestDTO;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.ports.repositories.CategoryRepository;
import com.fiap.techfood.domain.ports.repositories.ProductRepository;
import com.fiap.techfood.domain.ports.services.ProductServicePort;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ProductService implements ProductServicePort {

  private final ProductRepository repo;

  private final CategoryRepository categoryRepository;

  public ProductService(final ProductRepository repo, CategoryRepository categoryRepository) {
    this.repo = repo;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Long createProduct(ProductRequestDTO dto) {
    Product product = Product.fromProductDTO(dto);

    Category category =
        categoryRepository
            .findById(dto.getCategoryId())
            .orElseThrow(
                () -> new BusinessException("Invalid Category ID", HttpStatus.BAD_REQUEST));
    product.setCategory(category);

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
  public void updateProduct(Long id, ProductRequestDTO dto) {
    Product product = Product.fromProductDTO(dto);
    product.setId(id);

    Category category =
        categoryRepository
            .findById(dto.getCategoryId())
            .orElseThrow(
                () -> new BusinessException("Invalid Category ID", HttpStatus.BAD_REQUEST));
    product.setCategory(category);
    repo.updateProduct(product);
  }
}
