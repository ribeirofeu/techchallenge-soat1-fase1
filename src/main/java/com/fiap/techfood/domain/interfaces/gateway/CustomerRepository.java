package com.fiap.techfood.domain.interfaces.gateway;

import com.fiap.techfood.domain.entities.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Long save(Customer customer);

    Optional<Customer> findByCpf(String cpf);

    Optional<Customer> findById(Long id);
}
