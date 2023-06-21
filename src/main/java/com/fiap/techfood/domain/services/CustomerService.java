package com.fiap.techfood.domain.services;

import com.fiap.techfood.domain.Customer;
import com.fiap.techfood.domain.dto.request.CustomerRequestDTO;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.domain.ports.repositories.CustomerRepository;
import com.fiap.techfood.domain.ports.services.CustomerServicePort;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class CustomerService implements CustomerServicePort {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Long createCustomer(CustomerRequestDTO dto) {
        Customer customer = Customer.fromCustomerDto(dto);
        customerRepository.save(customer);
        return customer.getId();
    }

    @Override
    public Customer findCustomerByCpf(String cpf) {
        Optional<Customer> customer = customerRepository.findByCpf(cpf);
        return customer.orElseThrow(() -> new BusinessException("Customer not found by CPF", HttpStatus.NOT_FOUND));
    }
}
