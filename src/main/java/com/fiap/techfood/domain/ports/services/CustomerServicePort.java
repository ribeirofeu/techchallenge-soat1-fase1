package com.fiap.techfood.domain.ports.services;

import com.fiap.techfood.domain.Customer;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.request.CustomerRequestDTO;
import com.fiap.techfood.domain.dto.request.ProductRequestDTO;

import java.util.List;

public interface CustomerServicePort {

    Long createCustomer(CustomerRequestDTO dto);

    Customer findCustomerByCpf(String cpf);
}
