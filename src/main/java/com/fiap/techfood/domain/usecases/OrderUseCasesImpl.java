package com.fiap.techfood.domain.usecases;

import com.fiap.techfood.domain.dto.request.OrderRequestDTO;
import com.fiap.techfood.domain.entities.*;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.interfaces.gateway.CustomerRepository;
import com.fiap.techfood.domain.interfaces.gateway.OrderRepository;
import com.fiap.techfood.domain.interfaces.gateway.ProductRepository;
import com.fiap.techfood.domain.interfaces.usecases.OrderUseCases;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderUseCasesImpl implements OrderUseCases {

    private final OrderRepository repo;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public OrderUseCasesImpl(final OrderRepository orderRepository,
                             final ProductRepository productRepository,
                             final CustomerRepository customerRepository) {
        this.repo = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order createOrder(OrderRequestDTO requestDTO) {
        Order order = Order.fromOrderRequestDTO(requestDTO);

        if (requestDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                    .orElseThrow(() -> new BusinessException("Customer provided not found"));
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

    @Override
    public Order updateOrderStatus(Long orderNumber, OrderStatus status) {
        Order order = repo.findById(orderNumber).orElseThrow(() -> new BusinessException("Ordem não encontrada!"));
        order.setStatus(status);
        repo.updateOrderStatus(order);
        return order;
    }

    @Override
    public List<Order> findOrdersByStatusAndTimeInterval(OrderStatus status, OffsetDateTime startDateTime, OffsetDateTime endDateTime) {
        if (startDateTime == null) {
            startDateTime = OffsetDateTime.now().minusDays(1);
        }

        if (endDateTime == null) {
            endDateTime = OffsetDateTime.now();
        }

        if (endDateTime.isBefore(startDateTime)) {
            throw new BusinessException("A data final deve ser posterior a data inicial!");
        }

        if (status == null) {
            throw new BusinessException("O status não pode ser nulo");
        }

        return repo.findOrdersByStatusAndTimeInterval(status, startDateTime, endDateTime);
    }

    private static List<Long> getProductIds(List<OrderRequestDTO.OrderItemRequestDTO> items) {
        return items.stream().map(OrderRequestDTO.OrderItemRequestDTO::getProductId)
                .collect(Collectors.toList());
    }

    private List<Product> getAndValidateProducts(List<Long> productIds) {
        List<Product> products = productRepository.findAllByIdIn(productIds);
        if (products.size() != productIds.size()) {
            throw new BusinessException("Produto(s) não encontrado");
        }
        return products;
    }

    private BigDecimal calculateOrderTotalValue(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::calculateItemTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<OrderItem> buildOrderItems(List<OrderRequestDTO.OrderItemRequestDTO> requestItems, List<Product> products) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderRequestDTO.OrderItemRequestDTO item : requestItems) {
            Product currentProduct = findProductById(products, item.getProductId());
            OrderItem orderItem = createOrderItem(currentProduct, item);
            orderItems.add(orderItem);
        }

        return orderItems;
    }

    private Product findProductById(List<Product> products, Long productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        throw new BusinessException("Product not found with ID: " + productId);
    }

    private OrderItem createOrderItem(Product product, OrderRequestDTO.OrderItemRequestDTO item) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(item.getQuantity());
        orderItem.setUnitPrice(product.getPrice());
        orderItem.setProduct(product);
        return orderItem;
    }
}
