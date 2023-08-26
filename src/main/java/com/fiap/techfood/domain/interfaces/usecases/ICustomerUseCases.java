package com.fiap.techfood.domain.interfaces.usecases;

import com.fiap.techfood.domain.dto.request.CustomerRequestDTO;
import com.fiap.techfood.domain.entities.Customer;

public interface ICustomerUseCases {
    Long createCustomer(CustomerRequestDTO dto);

    Customer findCustomerByCpf(String cpf);
}
