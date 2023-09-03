package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.order.OrderStatus;
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
    @Modifying
    @Query("update Order o set o.status = :status where o.id = :id")
    void updateOrderStatus(@Param(value = "id") long id, @Param(value = "status") OrderStatus status);

    @Query(nativeQuery = true, value = """
            select c.id as "customerid", c.cpf, c.email, c.name, o.*, oi.*, p.id as "productid", p.description, p.name, p.price, p.category_id from `order` o inner join customer c ON c.id = o.customer_id
            inner join order_item oi ON oi.order_id = o.id
            inner join product p on p.id = oi.product_id
            where o.status NOT IN (0, 4 , 5) order by FIELD(o.status, 3, 2, 1), `datetime`;
            """)
    List<OrderEntity> findAllNotCompleted();

    @EntityGraph(attributePaths = { "items", "items.product", "items.product.category" })
    List<OrderEntity> findAllByStatusNotIn(List<OrderStatus> statuses);

    @EntityGraph(attributePaths = { "items", "items.product", "items.product.category" })
    List<OrderEntity> findAllByStatusIsAndCreatedDateTimeBetween(OrderStatus status, OffsetDateTime startDateTime, OffsetDateTime endDateTime);
}
