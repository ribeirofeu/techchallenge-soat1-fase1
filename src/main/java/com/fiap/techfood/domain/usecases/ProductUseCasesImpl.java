package com.fiap.techfood.domain.usecases;

import com.fiap.techfood.domain.dto.request.ProductRequestDTO;
import com.fiap.techfood.domain.entities.Category;
import com.fiap.techfood.domain.entities.Product;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.interfaces.gateway.CategoryRepository;
import com.fiap.techfood.domain.interfaces.gateway.ProductRepository;
import com.fiap.techfood.domain.interfaces.usecases.ProductUseCases;

import java.util.List;

public class ProductUseCasesImpl implements ProductUseCases {

    private final ProductRepository repo;

    private final CategoryRepository categoryRepository;

    public ProductUseCasesImpl(final ProductRepository repo, CategoryRepository categoryRepository) {
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
                                () -> new BusinessException("Invalid Category ID"));
        product.setCategory(category);

        repo.save(product);
        return product.getId();
    }

    @Override
    public Product findProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new BusinessException("Product ID not found"));
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
                                () -> new BusinessException("Invalid Category ID"));
        product.setCategory(category);
        repo.updateProduct(product);
    }
}
