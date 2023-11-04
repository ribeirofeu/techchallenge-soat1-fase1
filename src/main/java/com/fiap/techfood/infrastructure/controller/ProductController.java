package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Produtos")
public class ProductController {

    private final ProductUseCases useCases;

    @Autowired
    public ProductController(ProductUseCases service) {
        this.useCases = service;
    }

    @PostMapping
    @Operation(summary = "Criação de produto")
    ResponseEntity<Long> createProduct (@RequestBody ProductRequestDTO request) {
        Long id = useCases.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna os dados de um produto")
    ResponseEntity<Product> findProduct (@PathVariable Long id) {
       return  ResponseEntity.ok(useCases.findProductById(id));
    }

    @GetMapping()
    @Operation(summary = "Lista todos os produtos")
    ResponseEntity<List<Product>> findAllProducts () {
        return  ResponseEntity.ok(useCases.findAllProducts());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um produto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        useCases.deleteProduct(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto")
    ResponseEntity<String> updateProduct (@RequestBody ProductRequestDTO request, @PathVariable Long id) {
        useCases.updateProduct(id, request);
        return ResponseEntity.ok("Product Updated Successful");
    }

}
