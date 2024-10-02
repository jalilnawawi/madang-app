package com.jalil_be_app.madang_app.dto.orderDto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderResponseDto {
    private UUID orderId;
    private String username;
    private String restaurantName;
    private boolean completed;
}
