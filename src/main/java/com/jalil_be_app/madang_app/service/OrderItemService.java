package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.orderItemDto.request.CreateOrderItemRequestDto;
import com.jalil_be_app.madang_app.dto.orderItemDto.request.UpdateQtyOrderItemRequestDto;
import com.jalil_be_app.madang_app.dto.orderItemDto.response.CreateOrderItemResponseDto;
import com.jalil_be_app.madang_app.dto.orderItemDto.response.GetOrderItemResponseDto;
import com.jalil_be_app.madang_app.dto.orderItemDto.response.UpdateQtyOrderItemResponseDto;
import com.jalil_be_app.madang_app.model.entity.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {
    CreateOrderItemResponseDto createOrderItem(String token, CreateOrderItemRequestDto createOrderItemRequestDto);
    UpdateQtyOrderItemResponseDto updateQtyOrderItem(UUID orderItemId, UpdateQtyOrderItemRequestDto updateQtyOrderItemRequestDto);
    void deleteOrderItem(UUID orderItemId);
    List<GetOrderItemResponseDto> getAllbyOrderId(UUID orderId);
}
