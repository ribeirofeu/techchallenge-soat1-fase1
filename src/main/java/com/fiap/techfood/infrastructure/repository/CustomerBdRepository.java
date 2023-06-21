package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.Customer;
import com.fiap.techfood.domain.ports.repositories.CustomerRepository;
import com.fiap.techfood.infrastructure.repository.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class CustomerBdRepository implements CustomerRepository {

    private final SpringCustomerRepository springCustomerRepository;

    public CustomerBdRepository(SpringCustomerRepository springCustomerRepository) {
        this.springCustomerRepository = springCustomerRepository;
    }

    @Override
    public void save(Customer customer) {
        CustomerEntity entity = springCustomerRepository.save(new CustomerEntity(customer));
        customer.setId(entity.getId());
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        Optional<CustomerEntity> entity = springCustomerRepository.findByCpf(cpf);
        if (entity.isPresent()) {
            return Optional.of(entity.get().toCustomer());
        } else {
            return Optional.empty();
        }
    }
}
