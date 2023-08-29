package com.fiap.techfood.infrastructure.configuration;

import com.fiap.techfood.domain.interfaces.usecases.CategoryUseCases;
import com.fiap.techfood.domain.interfaces.usecases.CustomerUseCases;
import com.fiap.techfood.domain.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.domain.interfaces.usecases.ProductUseCases;
import com.fiap.techfood.domain.usecases.CategoryUseCasesImpl;
import com.fiap.techfood.domain.usecases.CustomerUseCasesImpl;
import com.fiap.techfood.domain.usecases.OrderUseCasesImpl;
import com.fiap.techfood.domain.usecases.ProductUseCasesImpl;
import com.fiap.techfood.infrastructure.repository.CategoryBdRepository;
import com.fiap.techfood.infrastructure.repository.CustomerBdRepository;
import com.fiap.techfood.infrastructure.repository.OrderBdRepository;
import com.fiap.techfood.infrastructure.repository.ProductBdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductUseCases productUseCases(ProductBdRepository repository, CategoryBdRepository categoryRepository) {
        return new ProductUseCasesImpl(repository, categoryRepository);
    }

    @Bean
    CategoryUseCases categoryUseCases(CategoryBdRepository repository) {
        return new CategoryUseCasesImpl(repository);
    }

    @Bean
    OrderUseCases orderUseCases(OrderBdRepository orderBdRepository, ProductBdRepository productRepository, CustomerBdRepository customerBdRepository) {
        return new OrderUseCasesImpl(orderBdRepository, productRepository, customerBdRepository);
    }

    @Bean
    CustomerUseCases customerUseCases(CustomerBdRepository repository) {
        return new CustomerUseCasesImpl(repository);
    }
}
