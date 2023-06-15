package com.fiap.techfood.application.controller;

import com.fiap.techfood.domain.Category;
import com.fiap.techfood.domain.Product;
import com.fiap.techfood.domain.dto.CategoryDTO;
import com.fiap.techfood.domain.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<Long> createCategory (@RequestBody CategoryDTO request) {
        Long id = service.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    ResponseEntity<Category> findCategory (@PathVariable Long id) {
       return  ResponseEntity.ok(service.findCategoryById(id));
    }

    @GetMapping()
    ResponseEntity<List<Category>> findAllCategories () {
        return  ResponseEntity.ok(service.findAllCategories());
    }

    @GetMapping("/{id}/products")
    ResponseEntity<List<Product>> findProductByCategory (@PathVariable Long id) {
        return  ResponseEntity.ok(service.findProductByCategory(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> updateCategory (@RequestBody CategoryDTO request, @PathVariable Long id) {
        service.updateCategory(id, request);
        return ResponseEntity.ok("Category Updated Successful");
    }

}
