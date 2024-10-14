package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.orderDetailDto.request.CreateOrderItemRequestDto;
import com.jalil_be_app.madang_app.dto.orderDetailDto.response.CreateOrderItemResponseDto;

import java.util.UUID;

public interface OrderItemService {
    CreateOrderItemResponseDto createOrderItem(CreateOrderItemRequestDto createOrderItemRequestDto);
    void deleteOrderItem(UUID orderItemId);
}
