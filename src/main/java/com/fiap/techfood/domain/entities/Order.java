package com.fiap.techfood.domain.entities;

import com.fiap.techfood.domain.dto.request.OrderRequestDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class Order {
    private Long number;
    private Customer customer;
    private List<OrderItem> items;
    private BigDecimal totalValue;
    private OrderStatus status;
    private OffsetDateTime dateTime;
    private String notes;

    public static Order fromOrderRequestDTO(OrderRequestDTO requestDTO) {
        Order order = new Order();
        order.setNotes(requestDTO.getNotes());
        return order;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
