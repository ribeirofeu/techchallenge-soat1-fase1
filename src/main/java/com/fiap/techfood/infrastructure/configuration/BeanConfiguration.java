package com.fiap.techfood.infrastructure.configuration;

import com.fiap.techfood.domain.ports.services.CategoryServicePort;
import com.fiap.techfood.domain.ports.services.ProductServicePort;
import com.fiap.techfood.domain.services.CategoryService;
import com.fiap.techfood.domain.services.ProductService;
import com.fiap.techfood.infrastructure.repository.CategoryBdRepository;
import com.fiap.techfood.infrastructure.repository.ProductBdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductServicePort productService (ProductBdRepository repository) {
        return new ProductService(repository);
    }

    @Bean
    CategoryServicePort categoryService (CategoryBdRepository repository) {
    return new CategoryService(repository);
    }
}
