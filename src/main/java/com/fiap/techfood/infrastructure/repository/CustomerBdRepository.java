package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.application.interfaces.gateways.CustomerRepository;
import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.infrastructure.repository.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerBdRepository implements CustomerRepository {

    private final SpringCustomerRepository springCustomerRepository;

    public CustomerBdRepository(SpringCustomerRepository springCustomerRepository) {
        this.springCustomerRepository = springCustomerRepository;
    }

    @Override
    public Long save(Customer customer) {
        CustomerEntity entity = springCustomerRepository.save(new CustomerEntity(customer));
        return entity.getId();
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        Optional<CustomerEntity> entity = springCustomerRepository.findByCpf(cpf);
        return entity.map(CustomerEntity::toCustomer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return springCustomerRepository.findById(id).map(CustomerEntity::toCustomer);
    }
}
