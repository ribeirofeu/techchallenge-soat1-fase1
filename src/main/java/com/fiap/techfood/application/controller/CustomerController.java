package com.fiap.techfood.application.controller;

import com.fiap.techfood.domain.dto.request.CustomerRequestDTO;
import com.fiap.techfood.domain.entities.Customer;
import com.fiap.techfood.domain.interfaces.usecases.ICustomerUseCases;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@Tag(name = "Clientes")
public class CustomerController {

    private final ICustomerUseCases service;

    @Autowired
    public CustomerController(ICustomerUseCases service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cria um cliente")
    ResponseEntity<Long> createCustomer(@RequestBody CustomerRequestDTO request) {
        Long id = service.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Retorna os dados do cliente a partir de um CPF")
    ResponseEntity<Customer> findCustomerByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.findCustomerByCpf(cpf));
    }
}
