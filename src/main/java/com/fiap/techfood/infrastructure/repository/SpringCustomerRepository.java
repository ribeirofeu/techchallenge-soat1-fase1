package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.infrastructure.repository.entity.CustomerEntity;
import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringCustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByCpf(String cpf);
}
