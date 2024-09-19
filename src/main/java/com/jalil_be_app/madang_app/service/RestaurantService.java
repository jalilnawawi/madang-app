package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.restaurantDto.request.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.response.CreateRestaurantResponseDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.request.UpdateRestaurantAddressRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.response.UpdateRestaurantAddressResponseDto;

import java.util.UUID;

public interface RestaurantService {
    CreateRestaurantResponseDto create(String token, CreateRestaurantRequestDto createRestaurantRequestDto);
    UpdateRestaurantAddressResponseDto update(String token, UpdateRestaurantAddressRequestDto updateRestaurantAddressRequestDto);
    void delete(String token, UUID restaurantId);

}
