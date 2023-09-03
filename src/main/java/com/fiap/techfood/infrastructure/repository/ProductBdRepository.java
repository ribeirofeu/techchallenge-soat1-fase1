package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.application.interfaces.gateways.ProductRepository;
import com.fiap.techfood.domain.commons.HttpStatusCodes;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

      return entity.map(ProductEntity::toProduct);
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
      throw new BusinessException("Product not found", HttpStatusCodes.NOT_FOUND);
    }
  }

  @Override
  public void updateProduct(Product product) {
    try {
      ProductEntity entity = new ProductEntity(product);
      repo.save(entity);
    } catch (DataIntegrityViolationException e) {
      throw new BusinessException("Error updating Product", HttpStatusCodes.BAD_REQUEST);
    }
  }
}
