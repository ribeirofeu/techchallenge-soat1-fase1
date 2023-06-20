package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.infrastructure.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringOrderRepository extends JpaRepository<OrderEntity, Long> {
}
