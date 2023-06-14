package com.fiap.techfood.application.controller;

import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.ProductDTO;
import com.fiap.techfood.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<Long> createProduct (@RequestBody ProductDTO request) {
        Long id = service.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> findProduct (@PathVariable Long id) {
       return  ResponseEntity.ok(service.findProductById(id));
    }

    @GetMapping()
    ResponseEntity<List<Product>> findAllProducts () {
        return  ResponseEntity.ok(service.findAllProducts());
    }

}
