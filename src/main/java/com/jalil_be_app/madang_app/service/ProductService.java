package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.productDto.request.CreateProductRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.request.UpdateProductRatingRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.response.*;
import com.jalil_be_app.madang_app.dto.productDto.request.UpdateProductPriceRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    CreateProductResponseDto create(String token, CreateProductRequestDto createProductRequestDto, MultipartFile file) throws IOException;
    List<GetAllProductResponseDto> getAllProduct();
    GetProductResponseDto getProductById(UUID productId);
    List<GetAllProductByRestoIdResponseDto> getProductByRestoId(UUID restaurantId);
    UpdateProductPriceResponseDto update(String token, UUID productId, UpdateProductPriceRequestDto updateProductPriceRequestDto);
    void delete(String token, UUID productId);
    List<GetProductBySearchResponseDto> getProductBySearch(String searchText);
    UpdateProductRatingResponseDto addRating(UUID productId, UpdateProductRatingRequestDto updateProductRatingRequestDto);
}
