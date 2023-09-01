package com.fiap.techfood.application.usecases;

import com.fiap.techfood.domain.Customer;
import com.fiap.techfood.application.dto.request.CustomerRequestDTO;
import com.fiap.techfood.domain.exception.BusinessException;
import com.fiap.techfood.application.interfaces.gateways.CustomerRepository;
import com.fiap.techfood.application.interfaces.usecases.CustomerUseCases;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class CustomerService implements CustomerUseCases {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Long createCustomer(CustomerRequestDTO dto) {
        Customer customer = Customer.fromCustomerDto(dto);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByCpf(String cpf) {
        Optional<Customer> customer = customerRepository.findByCpf(cpf);
        return customer.orElseThrow(() -> new BusinessException("Customer not found by CPF", HttpStatus.NOT_FOUND));
    }
}
