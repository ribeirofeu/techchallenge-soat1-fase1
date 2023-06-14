package com.fiap.techfood.infrastructure.repository;


import com.fiap.techfood.infrastructure.repository.entity.CategoryEntity;
import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringCategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
