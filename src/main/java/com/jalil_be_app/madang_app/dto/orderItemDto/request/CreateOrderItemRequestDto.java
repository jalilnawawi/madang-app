package com.jalil_be_app.madang_app.dto.orderItemDto.request;

import lombok.Data;

@Data
public class CreateOrderItemRequestDto {
    private String orderId;
    private String productId;
    private int quantity;
}
