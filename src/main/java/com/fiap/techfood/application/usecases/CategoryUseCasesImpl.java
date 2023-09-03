package com.fiap.techfood.application.usecases;

import com.fiap.techfood.domain.commons.HttpStatusCodes;
import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.application.interfaces.usecases.CategoryUseCases;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class CategoryUseCasesImpl implements CategoryUseCases {

  private final CategoryRepository repo;

  public CategoryUseCasesImpl(final CategoryRepository repo) {
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
    return repo.findById(id).orElseThrow(() -> new BusinessException("Category ID not found", HttpStatusCodes.NOT_FOUND));
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
            throw new BusinessException("Category ID invalid", HttpStatusCodes.NOT_FOUND);
        }
    }
}
