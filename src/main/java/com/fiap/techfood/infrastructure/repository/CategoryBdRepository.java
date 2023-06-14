package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.repository.CategoryRepository;
import com.fiap.techfood.domain.repository.ProductRepository;
import com.fiap.techfood.infrastructure.repository.entity.CategoryEntity;
import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
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

        if (entity.isPresent()){
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

        if (entity.isPresent()){
            return entity.get().getProducts();
        } else {
            return Collections.emptyList();
        }
    }

}
