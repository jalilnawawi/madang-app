package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.restaurantDto.request.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.response.CreateRestaurantResponseDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.request.UpdateRestaurantAddressRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.response.GetAllRestaurantResponseDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.response.GetRestaurantResponseDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.response.UpdateRestaurantAddressResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    CreateRestaurantResponseDto create(String token, CreateRestaurantRequestDto createRestaurantRequestDto, MultipartFile file) throws IOException;
    UpdateRestaurantAddressResponseDto update(String token, UpdateRestaurantAddressRequestDto updateRestaurantAddressRequestDto);
    void delete(UUID restaurantId);
    List<GetAllRestaurantResponseDto> getAllRestaurant();
    GetRestaurantResponseDto getRestaurantById(UUID restaurantId);
    List<GetAllRestaurantResponseDto> getRestaurantByUserId(UUID userId);
    List<GetAllRestaurantResponseDto> getRestaurantBySearch(String searchText);
}
