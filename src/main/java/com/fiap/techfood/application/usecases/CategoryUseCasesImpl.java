package com.fiap.techfood.application.usecases;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.application.interfaces.usecases.CategoryUseCases;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CategoryService implements CategoryUseCases {

  private final CategoryRepository repo;

  public CategoryService(final CategoryRepository repo) {
    this.repo = repo;
  }

  @Override
  public Long createCategory(CategoryRequestDTO dto) {
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
    public void updateCategory(Long id, CategoryRequestDTO dto) {
        try {
            Category category = Category.fromCategoryDto(dto);
            category.setId(id);
            repo.save(category);
        }catch (DataIntegrityViolationException e) {
            throw new BusinessException("Category ID invalid", HttpStatus.NOT_FOUND);
        }
    }
}
