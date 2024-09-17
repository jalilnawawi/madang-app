package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.productDto.CreateProductRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.CreateProductResponseDto;

public interface ProductService {
    CreateProductResponseDto create(String token, CreateProductRequestDto createProductRequestDto);
}
