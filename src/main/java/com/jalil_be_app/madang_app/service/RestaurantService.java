package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantResponseDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.UpdateRestaurantAddressRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.UpdateRestaurantAddressResponseDto;

import java.util.UUID;

public interface RestaurantService {
    CreateRestaurantResponseDto create(String token, CreateRestaurantRequestDto createRestaurantRequestDto);
    UpdateRestaurantAddressResponseDto update(UUID restaurantId, UpdateRestaurantAddressRequestDto updateRestaurantAddressRequestDto);
    void delete(UUID restaurantId);

}
