package com.fiap.techfood.domain.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.techfood.application.dto.request.CustomerRequestDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    private Long id;
    private String name;
    private String cpf;
    private String email;

    public static Customer fromCustomerDto(CustomerRequestDTO dto) {
        return Customer.builder().name(dto.getName()).cpf(dto.getCpf()).email(dto.getEmail()).build();
    }
}
