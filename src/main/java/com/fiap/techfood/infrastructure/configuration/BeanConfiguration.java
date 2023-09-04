package com.fiap.techfood.infrastructure.configuration;

import com.fiap.techfood.application.interfaces.usecases.*;
import com.fiap.techfood.application.usecases.*;
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
    OrderUseCases orderUseCases(OrderBdRepository orderBdRepository,
                                ProductBdRepository productRepository,
                                CustomerBdRepository customerBdRepository,
                                PaymentUseCases paymentService) {
        return new OrderUseCasesImpl(orderBdRepository, productRepository, customerBdRepository, paymentService);
    }

    @Bean
    CustomerUseCases customerUseCases(CustomerBdRepository repository) {
        return new CustomerUseCasesImpl(repository);
    }

    @Bean
    PaymentUseCases paymentUseCases() {
        return new PaymentUseCasesImpl();
    }
}
