package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String email;

    public CustomerEntity(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.email = customer.getEmail();
    }
    public Customer toCustomer() {
        return Customer.builder().id(id).name(name).cpf(cpf).email(email).build();
    }
}
