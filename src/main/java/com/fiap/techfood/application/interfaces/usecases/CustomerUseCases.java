package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.application.dto.request.CustomerRequestDTO;

public interface CustomerUseCases {

    Long createCustomer(CustomerRequestDTO dto);

    Customer findCustomerByCpf(String cpf);
}
