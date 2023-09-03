package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.application.dto.request.CustomerRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.CustomerUseCases;
import com.fiap.techfood.domain.customer.Customer;
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

    private final CustomerUseCases useCases;

    @Autowired
    public CustomerController(CustomerUseCases service) {
        this.useCases = service;
    }

    @PostMapping
    @Operation(summary = "Cria um cliente")
    ResponseEntity<Long> createCustomer (@RequestBody CustomerRequestDTO request) {
        Long id = useCases.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Retorna os dados do cliente a partir de um CPF")
    ResponseEntity<Customer> findCustomerByCpf (@PathVariable String cpf) {
       return  ResponseEntity.ok(useCases.findCustomerByCpf(cpf));
    }
}
