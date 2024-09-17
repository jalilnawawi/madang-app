package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.productDto.CreateProductRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.CreateProductResponseDto;
import com.jalil_be_app.madang_app.dto.productDto.UpdateProductPriceRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.UpdateProductPriceResponseDto;

import java.util.UUID;

public interface ProductService {
    CreateProductResponseDto create(String token, CreateProductRequestDto createProductRequestDto);
    UpdateProductPriceResponseDto update(String token, UUID productId, UpdateProductPriceRequestDto updateProductPriceRequestDto);
}
