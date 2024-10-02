package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.orderDetailDto.request.CreateOrderItemRequestDto;
import com.jalil_be_app.madang_app.dto.orderDto.request.CreateOrderRequestDto;
import com.jalil_be_app.madang_app.model.entity.Order;
import com.jalil_be_app.madang_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, Object>> create(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateOrderRequestDto createOrderRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", orderService.createOrder(token, createOrderRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get-order-list")
    @PreAuthorize("hasRole('USER_ROLE')")
    public List<Order> getAll(){
        return orderService.getOrderList();
    }
}
