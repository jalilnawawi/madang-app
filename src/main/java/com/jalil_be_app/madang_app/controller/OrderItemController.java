package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.orderDetailDto.request.CreateOrderItemRequestDto;
import com.jalil_be_app.madang_app.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/order-item")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrderItem(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateOrderItemRequestDto createOrderItemRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", orderItemService.createOrderItem(createOrderItemRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
