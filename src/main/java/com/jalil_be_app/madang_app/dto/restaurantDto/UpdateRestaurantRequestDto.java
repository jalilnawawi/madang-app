package com.jalil_be_app.madang_app.dto.restaurantDto;

import lombok.Data;

@Data
public class UpdateRestaurantRequestDto {
    private String name;
    private String description;
    private String address;
}