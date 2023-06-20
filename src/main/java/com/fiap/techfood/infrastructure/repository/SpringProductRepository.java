package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findAllByCategoryName(String name);

    List<ProductEntity> findAllByIdIn(List<Long> ids);

}
