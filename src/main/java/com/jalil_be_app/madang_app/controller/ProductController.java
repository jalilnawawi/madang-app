package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.productDto.request.CreateProductRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.request.UpdateProductPriceRequestDto;
import com.jalil_be_app.madang_app.model.entity.Product;
import com.jalil_be_app.madang_app.service.ProductService;
import com.jalil_be_app.madang_app.utils.ApiResponseAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @ApiResponseAnnotations.CreateProductResponses
    public ResponseEntity<Map<String, Object>> create(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateProductRequestDto createProductRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "new Product added to your Restaurant!");
        response.put("data", productService.create(token, createProductRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get-all-product")
//    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @ApiResponseAnnotations.GetAllProductResponses
    public ResponseEntity<Map<String, Object>> getAll(){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", productService.getAllProduct());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-product-by-id/{id}")
//    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @ApiResponseAnnotations.GetProductByIdResponses
    public ResponseEntity<Map<String, Object>> getById(@PathVariable("id") UUID id){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", productService.getProductById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update-price/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @ApiResponseAnnotations.UpdateProductPriceResponses
    public ResponseEntity<Map<String, Object>> update(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") UUID productId,
            @RequestBody UpdateProductPriceRequestDto updateProductPriceRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success update price of product");
        response.put("data", productService.update(token, productId, updateProductPriceRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @ApiResponseAnnotations.DeleteProductResponses
    public void delete(@RequestHeader("Authorization") String token, @PathVariable("id") UUID productId){
        productService.delete(token, productId);
    }

    @GetMapping("/search")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, Object>> searchProduct(@RequestParam String searchText){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success get product related to " + searchText);
        response.put("data", productService.getProductBySearch(searchText));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-product-by-resto-id/{restaurantId}")
    @ApiResponseAnnotations.GetProductByRestoIdResponses
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable("restaurantId") UUID restaurantId){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", productService.getProductByRestoId(restaurantId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
