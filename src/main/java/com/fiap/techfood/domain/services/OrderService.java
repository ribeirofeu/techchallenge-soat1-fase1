package com.fiap.techfood.domain.services;

import com.fiap.techfood.domain.Customer;
import com.fiap.techfood.domain.Order;
import com.fiap.techfood.domain.OrderItem;
import com.fiap.techfood.domain.OrderStatus;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.dto.request.SearchOrdersRequestDTO;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.ports.repositories.CustomerRepository;
import com.fiap.techfood.domain.ports.repositories.OrderRepository;
import com.fiap.techfood.domain.ports.repositories.ProductRepository;
import com.fiap.techfood.domain.ports.services.OrderServicePort;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements OrderServicePort {
    private final OrderRepository repo;
    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    public OrderService(final OrderRepository orderRepository, final ProductRepository productRepository, final CustomerRepository customerRepository) {
        this.repo = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order createOrder(OrderRequestDTO requestDTO) {
        Order order = Order.fromOrderRequestDTO(requestDTO);

        if (requestDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                    .orElseThrow(() -> new BusinessException("Customer provided not found", HttpStatus.BAD_REQUEST));
            order.setCustomer(customer);
        }

        order.setStatus(OrderStatus.CREATED);
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
        for (OrderRequestDTO.OrderItemRequestDTO item : requestItems) {
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
    public Order updateOrderStatus(Long orderNumber, OrderStatus status, OffsetDateTime paymentDate) {
        Order order = repo.findById(orderNumber).orElseThrow(() -> new BusinessException("Ordem não encontrada!", HttpStatus.NOT_FOUND));
        order.setStatus(status);

        if (paymentDate != null) {
            order.setPaymentDate(paymentDate);
        }

        repo.updateOrderStatus(order);
        return order;
    }

    @Override
    public List<Order> findOrdersByStatusAndTimeInterval(SearchOrdersRequestDTO searchOrdersRequestDTO) {
        var validStartDateTime = ObjectUtils.firstNonNull(searchOrdersRequestDTO.getStartDatetime(), OffsetDateTime.now().minusDays(1));
        var validEndDateTime = ObjectUtils.firstNonNull(searchOrdersRequestDTO.getEndDatetime(), OffsetDateTime.now());

        if (validEndDateTime.isBefore(validStartDateTime)) {
            throw new BusinessException("A data final deve ser posterior a data inicial!", HttpStatus.BAD_REQUEST);
        }

        return repo.findOrdersByStatusAndTimeInterval(searchOrdersRequestDTO.getStatus(), validStartDateTime, validEndDateTime);
    }
}
