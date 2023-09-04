package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.CategoryUseCases;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categorias")
public class CategoryController {
    private final CategoryUseCases useCases;

    @Autowired
    public CategoryController(CategoryUseCases service) {
        this.useCases = service;
    }

    @PostMapping
    @Operation(summary = "Cria uma categoria")
    ResponseEntity<Long> createCategory (@RequestBody CategoryRequestDTO request) {
        Long id = useCases.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna os dados de uma categoria")
    ResponseEntity<Category> findCategory (@PathVariable Long id) {
       return  ResponseEntity.ok(useCases.findCategoryById(id));
    }

    @GetMapping()
    @Operation(summary = "Lista todas as categorias")
    ResponseEntity<List<Category>> findAllCategories () {
        return  ResponseEntity.ok(useCases.findAllCategories());
    }

    @GetMapping("/{id}/products")
    @Operation(summary = "Lista todas os produtos de uma categoria")
    ResponseEntity<List<Product>> findProductByCategory (@PathVariable Long id) {
        return  ResponseEntity.ok(useCases.findProductByCategory(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove uma categoria")
    public void deleteCategory(@PathVariable Long id) {
        useCases.deleteCategory(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma categoria")
    ResponseEntity<String> updateCategory (@RequestBody CategoryRequestDTO request, @PathVariable Long id) {
        useCases.updateCategory(id, request);
        return ResponseEntity.ok("Category Updated Successful");
    }

}
