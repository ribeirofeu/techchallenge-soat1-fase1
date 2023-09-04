package com.fiap.techfood.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
    private String name;
    private String cpf;
    private String email;
}
