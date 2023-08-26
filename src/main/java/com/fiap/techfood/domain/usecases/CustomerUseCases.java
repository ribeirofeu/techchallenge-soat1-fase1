package com.fiap.techfood.domain.usecases;

import com.fiap.techfood.domain.dto.request.CustomerRequestDTO;
import com.fiap.techfood.domain.entities.Customer;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.interfaces.gateway.CustomerRepository;
import com.fiap.techfood.domain.interfaces.usecases.ICustomerUseCases;

import java.util.Optional;

public class CustomerUseCases implements ICustomerUseCases {

    private final CustomerRepository repo;

    public CustomerUseCases(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public Long createCustomer(CustomerRequestDTO dto) {
        Customer customer = Customer.fromCustomerDto(dto);
        return repo.save(customer);
    }

    @Override
    public Customer findCustomerByCpf(String cpf) {
        Optional<Customer> customer = repo.findByCpf(cpf);
        return customer.orElseThrow(() -> new BusinessException("Customer not found by CPF"));
    }
}
