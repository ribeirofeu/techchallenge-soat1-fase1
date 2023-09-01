package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.domain.Customer;
import com.fiap.techfood.application.dto.request.CustomerRequestDTO;

public interface CustomerUsesCases {

    Long createCustomer(CustomerRequestDTO dto);

    Customer findCustomerByCpf(String cpf);
}
