package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.orderDetailDto.request.CreateOrderItemRequestDto;
import com.jalil_be_app.madang_app.dto.orderDetailDto.request.UpdateQtyOrderItemRequestDto;
import com.jalil_be_app.madang_app.dto.orderDetailDto.response.CreateOrderItemResponseDto;
import com.jalil_be_app.madang_app.dto.orderDetailDto.response.UpdateQtyOrderItemResponseDto;
import com.jalil_be_app.madang_app.model.entity.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {
    CreateOrderItemResponseDto createOrderItem(String token, CreateOrderItemRequestDto createOrderItemRequestDto);
    UpdateQtyOrderItemResponseDto updateQtyOrderItem(UUID orderItemId, UpdateQtyOrderItemRequestDto updateQtyOrderItemRequestDto);
    void deleteOrderItem(UUID orderItemId);
    List<OrderItem> getAllbyOrderId(UUID orderId);
}
