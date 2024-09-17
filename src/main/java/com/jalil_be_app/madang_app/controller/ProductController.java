package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.productDto.CreateProductRequestDto;
import com.jalil_be_app.madang_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<Map<String, Object>> create(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateProductRequestDto createProductRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "new Product added to your Restaurant!");
        response.put("data", productService.create(token, createProductRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
