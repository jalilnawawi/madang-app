package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantResponseDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.UpdateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.UpdateRestaurantResponseDto;

public interface RestaurantService {
    CreateRestaurantResponseDto create(String token, CreateRestaurantRequestDto createRestaurantRequestDto);
    UpdateRestaurantResponseDto update(String token, UpdateRestaurantRequestDto updateRestaurantRequestDto);
}
