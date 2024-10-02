package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.productDto.request.CreateProductRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.response.CreateProductResponseDto;
import com.jalil_be_app.madang_app.dto.productDto.request.UpdateProductPriceRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.response.UpdateProductPriceResponseDto;
import com.jalil_be_app.madang_app.model.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    CreateProductResponseDto create(String token, CreateProductRequestDto createProductRequestDto);
    List<Product> getProductList();
    UpdateProductPriceResponseDto update(String token, UUID productId, UpdateProductPriceRequestDto updateProductPriceRequestDto);
    void delete(String token, UUID productId);
}
