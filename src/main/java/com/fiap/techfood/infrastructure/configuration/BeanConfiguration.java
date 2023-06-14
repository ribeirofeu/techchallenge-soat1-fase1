package com.fiap.techfood.infrastructure.configuration;

import com.fiap.techfood.domain.service.ProductService;
import com.fiap.techfood.domain.service.impl.ProductServiceImpl;
import com.fiap.techfood.infrastructure.repository.ProductBdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductService productService (ProductBdRepository repository) {
        return new ProductServiceImpl(repository);
    }
}
