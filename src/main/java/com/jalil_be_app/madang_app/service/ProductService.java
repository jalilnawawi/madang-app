package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.productDto.request.CreateProductRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.request.UpdateProductRatingRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.response.*;
import com.jalil_be_app.madang_app.dto.productDto.request.UpdateProductPriceRequestDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    CreateProductResponseDto create(String token, CreateProductRequestDto createProductRequestDto);
    List<GetAllProductResponseDto> getAllProduct();
    GetProductResponseDto getProductById(UUID productId);
    List<GetAllProductByRestoIdResponseDto> getProductByRestoId(UUID restaurantId);
    UpdateProductPriceResponseDto update(String token, UUID productId, UpdateProductPriceRequestDto updateProductPriceRequestDto);
    void delete(String token, UUID productId);
    List<GetAllProductResponseDto> getProductBySearch(String searchText);
    UpdateProductRatingResponseDto addRating(UUID productId, UpdateProductRatingRequestDto updateProductRatingRequestDto);
}
