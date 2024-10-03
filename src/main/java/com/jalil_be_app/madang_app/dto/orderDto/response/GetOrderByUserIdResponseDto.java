package com.jalil_be_app.madang_app.dto.orderDto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class GetOrderByUserIdResponseDto {
    private UUID orderId;
    private String username;
    private String restaurantName;
    private boolean completed;

    public GetOrderByUserIdResponseDto(UUID orderId, String username, String restaurantName, boolean completed) {
        this.orderId = orderId;
        this.username = username;
        this.restaurantName = restaurantName;
        this.completed = completed;
    }
}
