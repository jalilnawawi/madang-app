package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.orderDto.request.CreateOrderRequestDto;
import com.jalil_be_app.madang_app.dto.orderDto.response.CreateOrderResponseDto;
import com.jalil_be_app.madang_app.dto.orderDto.response.GetOrderByUserIdResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    CreateOrderResponseDto createOrder(String token, CreateOrderRequestDto createOrderRequestDto);
    List<GetOrderByUserIdResponseDto> getOrderByUserId(UUID userId);
}
