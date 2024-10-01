package com.jalil_be_app.madang_app.dto.orderDetailDto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemRequestDto {
    private UUID orderId;
    private UUID productId;
    private int quantity;
}
