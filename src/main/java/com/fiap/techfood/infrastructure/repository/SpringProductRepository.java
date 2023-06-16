package com.fiap.techfood.infrastructure.repository;


import com.fiap.techfood.domain.Product;
import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findAllByCategoryName(String name);

}
