package com.fiap.techfood.application.usecases;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.application.interfaces.gateways.ProductRepository;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ProductService implements ProductUseCases {

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
