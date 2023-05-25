package com.fiap.techfood.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello-world")
public class HelloWorld {
    @GetMapping()
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok("Hello World");
    }
}
