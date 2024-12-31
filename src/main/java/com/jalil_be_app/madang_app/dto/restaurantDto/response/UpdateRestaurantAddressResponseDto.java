package com.jalil_be_app.madang_app.dto.restaurantDto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateRestaurantAddressResponseDto {
    private UUID restaurantId;
    private UUID userId;
    private String address;
}
