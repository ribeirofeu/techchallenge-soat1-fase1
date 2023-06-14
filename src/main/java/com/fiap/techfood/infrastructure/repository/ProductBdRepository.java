package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.repository.ProductRepository;
import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductBdRepository implements ProductRepository {

    private final SpringProductRepository repo;

    public ProductBdRepository(SpringProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        Optional<ProductEntity> entity = repo.findById(id);

        if (entity.isPresent()){
            return Optional.of(entity.get().toProduct());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void save(Product product) {
        ProductEntity entity = repo.save(new ProductEntity(product));
        product.setId(entity.getId());
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntities = repo.findAll();
        return productEntities.stream().map(ProductEntity::toProduct).collect(Collectors.toList());
    }

    @Override
    public Optional<List<Product>> findByCategory(Category category) {
        //TODO Implement method
        return Optional.empty();
    }
}
