package com.fiap.techfood.application.controller;

import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.ports.services.ProductServicePort;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class HealthCheckController implements HealthIndicator {

    private final ProductServicePort service;

    public HealthCheckController(ProductServicePort service) {
        this.service = service;
    }


    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {
        try{
            List<Product> products = service.findAllProducts();
            return Health.up().withDetail("Total products:", products.size()).build();
        }catch (Exception e){
            return Health.down().withDetail("Total products:", 0).build();
        }
    }
}
