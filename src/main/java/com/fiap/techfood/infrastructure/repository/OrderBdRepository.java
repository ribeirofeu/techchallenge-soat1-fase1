package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.OrderStatus;
import com.fiap.techfood.domain.ports.repositories.OrderRepository;
import com.fiap.techfood.infrastructure.repository.entity.OrderEntity;
import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderBdRepository implements OrderRepository {

    @Autowired
    SpringOrderRepository repo;

    @Autowired
    SpringProductRepository productRepository;

    @Override
    public Optional<Order> findById(Long id) {
        Optional<OrderEntity> entity = repo.findById(id);
        return entity.map(OrderEntity::toOrder);
    }

    @Override
    @Transactional
    public Order save(Order order) {
        OrderEntity createdOrder = repo.save(OrderEntity.from(order));
        order.getItems().forEach(orderItem -> {
            ProductEntity product = productRepository.getReferenceById(orderItem.getProduct().getId());
            createdOrder.addItem(product, orderItem.getQuantity());
        });

        return createdOrder.toOrder();
    }

    @Override
    @Transactional
    public void updateOrderStatus(Order order) {
        repo.updateOrderStatus(order.getNumber(), order.getStatus());
    }

    @Override
    public List<Order> findOrdersByStatusAndTimeInterval(OrderStatus status, OffsetDateTime startDateTime, OffsetDateTime endDateTime) {
        return repo.findAllByStatusIsAndDateTimeBetween(status, startDateTime, endDateTime)
                .stream().map(OrderEntity::toOrder).collect(Collectors.toList());
    }
}
