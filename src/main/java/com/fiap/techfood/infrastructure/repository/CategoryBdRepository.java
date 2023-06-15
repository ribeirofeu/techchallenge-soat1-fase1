package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.repository.CategoryRepository;
import com.fiap.techfood.infrastructure.repository.entity.CategoryEntity;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CategoryBdRepository implements CategoryRepository {

  private final SpringCategoryRepository repo;

  public CategoryBdRepository(SpringCategoryRepository repo) {
    this.repo = repo;
  }

  @Override
  public Optional<Category> findById(Long id) {
    Optional<CategoryEntity> entity = repo.findById(id);

    if (entity.isPresent()) {
      return Optional.of(entity.get().toCategory());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public void save(Category category) {
    CategoryEntity entity = repo.save(new CategoryEntity(category));
    category.setId(entity.getId());
  }

  @Override
  public List<Category> findAll() {
    List<CategoryEntity> categoryEntities = repo.findAll();
    return categoryEntities.stream().map(CategoryEntity::toCategory).collect(Collectors.toList());
  }

  @Override
  public List<Product> findProductByCategory(Long id) {
    Optional<CategoryEntity> entity = repo.findById(id);

    if (entity.isPresent()) {
      return entity.get().getProducts();
    } else {
      return Collections.emptyList();
    }
  }

  @Override
  public void deleteCategory(Long id) {
    try {
      repo.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new BusinessException("Category not found", HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public void updateCategory(Category category) {
    try {
      CategoryEntity entity = new CategoryEntity(category);
      repo.save(entity);
    } catch (DataIntegrityViolationException e) {
      throw new BusinessException("Category ID invalid", HttpStatus.NOT_FOUND);
    }
  }
}

