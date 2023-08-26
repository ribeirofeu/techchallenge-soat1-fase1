package com.fiap.techfood.infrastructure.configuration;

import com.fiap.techfood.domain.interfaces.usecases.ICategoryUseCases;
import com.fiap.techfood.domain.interfaces.usecases.ICustomerUseCases;
import com.fiap.techfood.domain.interfaces.usecases.IOrderUseCases;
import com.fiap.techfood.domain.interfaces.usecases.IProductUseCases;
import com.fiap.techfood.domain.usecases.CategoryUseCases;
import com.fiap.techfood.domain.usecases.CustomerUseCases;
import com.fiap.techfood.domain.usecases.OrderUseCases;
import com.fiap.techfood.domain.usecases.ProductUseCases;
import com.fiap.techfood.infrastructure.repository.CategoryBdRepository;
import com.fiap.techfood.infrastructure.repository.CustomerBdRepository;
import com.fiap.techfood.infrastructure.repository.OrderBdRepository;
import com.fiap.techfood.infrastructure.repository.ProductBdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    IProductUseCases productService(ProductBdRepository repository, CategoryBdRepository categoryRepository) {
        return new ProductUseCases(repository, categoryRepository);
    }

    @Bean
    ICategoryUseCases categoryService(CategoryBdRepository repository) {
        return new CategoryUseCases(repository);
    }

    @Bean
    IOrderUseCases orderService(OrderBdRepository orderBdRepository, ProductBdRepository productRepository, CustomerBdRepository customerBdRepository) {
        return new OrderUseCases(orderBdRepository, productRepository, customerBdRepository);
    }

    @Bean
    ICustomerUseCases customerService(CustomerBdRepository repository) {
        return new CustomerUseCases(repository);
    }
}
