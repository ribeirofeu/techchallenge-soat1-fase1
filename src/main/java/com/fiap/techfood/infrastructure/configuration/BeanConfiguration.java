package com.fiap.techfood.infrastructure.configuration;

import com.fiap.techfood.domain.ports.services.CategoryServicePort;
import com.fiap.techfood.domain.ports.services.OrderServicePort;
import com.fiap.techfood.domain.ports.services.ProductServicePort;
import com.fiap.techfood.domain.services.CategoryService;
import com.fiap.techfood.domain.services.OrderService;
import com.fiap.techfood.domain.services.ProductService;
import com.fiap.techfood.infrastructure.repository.CategoryBdRepository;
import com.fiap.techfood.infrastructure.repository.OrderBdRepository;
import com.fiap.techfood.domain.ports.services.CustomerServicePort;
import com.fiap.techfood.domain.services.CustomerService;
import com.fiap.techfood.infrastructure.repository.CustomerBdRepository;
import com.fiap.techfood.infrastructure.repository.ProductBdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductServicePort productService (ProductBdRepository repository, CategoryBdRepository categoryRepository) {
        return new ProductService(repository, categoryRepository);
    }

    @Bean
    CategoryServicePort categoryService (CategoryBdRepository repository) {
    return new CategoryService(repository);
    }

    @Bean
    OrderServicePort orderService (OrderBdRepository orderBdRepository, ProductBdRepository productRepository, CustomerBdRepository customerBdRepository) {
        return new OrderService(orderBdRepository, productRepository, customerBdRepository);
    }

    @Bean
    CustomerServicePort customerService (CustomerBdRepository repository) {
        return new CustomerService(repository);
    }
}
