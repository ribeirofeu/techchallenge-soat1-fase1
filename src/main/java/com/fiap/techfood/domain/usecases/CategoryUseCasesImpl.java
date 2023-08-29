package com.fiap.techfood.domain.usecases;

import com.fiap.techfood.domain.dto.request.CategoryRequestDTO;
import com.fiap.techfood.domain.entities.Category;
import com.fiap.techfood.domain.entities.Product;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.interfaces.gateway.CategoryRepository;
import com.fiap.techfood.domain.interfaces.usecases.CategoryUseCases;

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
        return repo.findById(id).orElseThrow(() -> new BusinessException("Category ID not found"));
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
        } catch (Exception e) {
            throw new BusinessException("Category ID invalid");
        }
    }
}
