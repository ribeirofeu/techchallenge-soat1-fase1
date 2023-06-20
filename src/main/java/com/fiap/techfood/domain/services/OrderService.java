package com.fiap.techfood.domain.services;

import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.OrderItem;
import com.fiap.techfood.domain.OrderStatus;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.ports.repositories.OrderRepository;
import com.fiap.techfood.domain.ports.repositories.ProductRepository;
import com.fiap.techfood.domain.ports.services.OrderServicePort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements OrderServicePort {
    private final OrderRepository repo;
    private final ProductRepository productRepository;

    public OrderService(final OrderRepository orderRepository, final ProductRepository productRepository) {
        this.repo = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(OrderRequestDTO requestDTO) {
        Order order = Order.fromOrderRequestDTO(requestDTO);
        order.setStatus(OrderStatus.RECEIVED);
        order.setDateTime(OffsetDateTime.now());

        List<Product> products = getAndValidateProducts(getProductIds(requestDTO.getItems()));
        List<OrderItem> orderItems = buildOrderItems(requestDTO.getItems(), products);
        order.setItems(orderItems);
        order.setTotalValue(calculateOrderTotalValue(orderItems));

        return repo.save(order);
    }

    private BigDecimal calculateOrderTotalValue(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::calculateItemTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private List<OrderItem> buildOrderItems(List<OrderRequestDTO.OrderItemRequestDTO> requestItems, List<Product> products) {
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderRequestDTO.OrderItemRequestDTO item : requestItems) {
            Product currentProduct = products.stream().filter(product -> product.getId().equals(item.getProductId())).findFirst().orElseThrow();
            orderItems.add(OrderItem.builder()
                            .quantity(item.getQuantity())
                            .unitPrice(currentProduct.getPrice())
                            .product(currentProduct)
                    .build());
        }
        return orderItems;
    }

    private List<Product> getAndValidateProducts(List<Long> productIds) {
        List<Product> products = productRepository.findAllByIdIn(productIds);
        if (products.size() != productIds.size()) {
            throw new BusinessException("Produto(s) não encontrado", HttpStatus.NOT_FOUND);
        }
        return products;
    }

    private static List<Long> getProductIds(List<OrderRequestDTO.OrderItemRequestDTO> items) {
        return items.stream().map(OrderRequestDTO.OrderItemRequestDTO::getProductId)
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(Long orderNumber, OrderStatus status) {

    }

    @Override
    public List<Order> findOrdersByStatusAndTimeInterval(OrderStatus status, OffsetDateTime startDateTime, OffsetDateTime endDateTime) {
        return null;
    }
}
