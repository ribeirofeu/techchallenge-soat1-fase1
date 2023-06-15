package com.fiap.techfood.domain.service.impl;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.CategoryDTO;
import com.fiap.techfood.domain.dto.ProductDTO;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.repository.CategoryRepository;
import com.fiap.techfood.domain.repository.ProductRepository;
import com.fiap.techfood.domain.service.CategoryService;
import com.fiap.techfood.domain.service.ProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository repo;

  public CategoryServiceImpl(final CategoryRepository repo) {
    this.repo = repo;
  }

  @Override
  public Long createCategory(CategoryDTO dto) {
    Category category = Category.fromCategoryDto(dto);
    repo.save(category);
    return category.getId();
  }

  @Override
  public Category findCategoryById(Long id) {
    return repo.findById(id).orElseThrow(() -> new BusinessException("Category ID not found", HttpStatus.NOT_FOUND));
  }

  @Override
  public List<Product> findProductByCategory(Long id) {
    return repo.findProductByCategory(id);
  }

  @Override
  public List<Category> findAllCategories() {
    return repo.findAll();
  }

    @Override
    public void deleteCategory(Long id) {
        repo.deleteCategory(id);
    }

    @Override
    public void updateCategory(Long id, CategoryDTO dto) {
        try {
            Category category = Category.fromCategoryDto(dto);
            category.setId(id);
            repo.save(category);
        }catch (DataIntegrityViolationException e) {
            throw new BusinessException("Category ID invalid", HttpStatus.NOT_FOUND);
        }
    }
}
