package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantResponseDto;
import com.jalil_be_app.madang_app.model.entity.Restaurant;

public interface RestaurantService {
    CreateRestaurantResponseDto create(CreateRestaurantRequestDto createRestaurantRequestDto);
}
