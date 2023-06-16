package com.fiap.techfood.application.controller;

import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.request.ProductRequestDTO;
import com.fiap.techfood.domain.ports.services.ProductServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServicePort service;

    @Autowired
    public ProductController(ProductServicePort service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<Long> createProduct (@RequestBody ProductRequestDTO request) {
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> updateProduct (@RequestBody ProductRequestDTO request, @PathVariable Long id) {
        service.updateProduct(id, request);
        return ResponseEntity.ok("Product Updated Successful");
    }

}
