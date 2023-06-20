package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.ports.repositories.ProductRepository;
import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductBdRepository implements ProductRepository {

  private final SpringProductRepository repo;

  public ProductBdRepository(SpringProductRepository repo) {
    this.repo = repo;
  }

  @Override
  public Optional<Product> findById(Long id) {
    Optional<ProductEntity> entity = repo.findById(id);

    if (entity.isPresent()) {
      return Optional.of(entity.get().toProduct());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public void save(Product product) {
    ProductEntity entity = new ProductEntity(product);
    repo.save(entity);
    product.setId(entity.getId());
  }

  @Override
  public List<Product> findAll() {
    List<ProductEntity> productEntities = repo.findAll();
    return productEntities.stream().map(ProductEntity::toProduct).collect(Collectors.toList());
  }

  @Override
  public List<Product> findAllByIdIn(List<Long> productIds) {
    return repo.findAllByIdIn(productIds).stream().map(ProductEntity::toProduct).collect(Collectors.toList());
  }

  @Override
  public void deleteProduct(Long id) {
    try {
      repo.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new BusinessException("Product not found", HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public void updateProduct(Product product) {
    try {
      ProductEntity entity = new ProductEntity(product);
      repo.save(entity);
    } catch (DataIntegrityViolationException e) {
      throw new BusinessException("Product ID invalid", HttpStatus.NOT_FOUND);
    }
  }
}
