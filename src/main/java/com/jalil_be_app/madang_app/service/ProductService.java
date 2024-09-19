package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.productDto.request.CreateProductRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.response.CreateProductResponseDto;
import com.jalil_be_app.madang_app.dto.productDto.request.UpdateProductPriceRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.response.UpdateProductPriceResponseDto;

import java.util.UUID;

public interface ProductService {
    CreateProductResponseDto create(String token, CreateProductRequestDto createProductRequestDto);
    UpdateProductPriceResponseDto update(String token, UUID productId, UpdateProductPriceRequestDto updateProductPriceRequestDto);
    void delete(String token, UUID productId);
}
