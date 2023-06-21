package com.fiap.techfood.application.controller;

import com.fiap.techfood.domain.Customer;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.request.CustomerRequestDTO;
import com.fiap.techfood.domain.dto.request.ProductRequestDTO;
import com.fiap.techfood.domain.ports.services.CustomerServicePort;
import com.fiap.techfood.domain.ports.services.ProductServicePort;
import com.fiap.techfood.domain.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServicePort service;

    @Autowired
    public CustomerController(CustomerServicePort service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<Long> createCustomer (@RequestBody CustomerRequestDTO request) {
        Long id = service.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{cpf}")
    ResponseEntity<Customer> findCustomerByCpf (@PathVariable String cpf) {
       return  ResponseEntity.ok(service.findCustomerByCpf(cpf));
    }
}
