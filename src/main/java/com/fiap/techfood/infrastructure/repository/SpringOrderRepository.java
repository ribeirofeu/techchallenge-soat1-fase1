package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.OrderStatus;
import com.fiap.techfood.infrastructure.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface SpringOrderRepository extends JpaRepository<OrderEntity, Long> {
    @EntityGraph(attributePaths = { "items", "items.product", "items.product.category" })
    List<OrderEntity> findAllByStatusIsAndDateTimeBetween(OrderStatus status, OffsetDateTime startDateTime, OffsetDateTime endDateTime);
}
