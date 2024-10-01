package com.jalil_be_app.madang_app.dto.orderDetailDto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemResponseDto {
    private UUID orderItemId;
    private UUID orderId;
    private String productName;
    private int quantity;
}
