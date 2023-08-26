package com.fiap.techfood.domain.dto.request;

public class CustomerRequestDTO {
    private String name;
    private String cpf;
    private String email;

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
