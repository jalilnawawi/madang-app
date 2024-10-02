package com.jalil_be_app.madang_app.dto.orderDetailDto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderItemRequestDto {
    private String orderId;
    private String productId;
    private int quantity;
}
