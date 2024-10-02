package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.orderDto.request.CreateOrderRequestDto;
import com.jalil_be_app.madang_app.dto.orderDto.response.CreateOrderResponseDto;
import com.jalil_be_app.madang_app.model.entity.Order;

import java.util.List;

public interface OrderService {
    CreateOrderResponseDto createOrder(String token, CreateOrderRequestDto createOrderRequestDto);
    List<Order> getOrderList();
}
