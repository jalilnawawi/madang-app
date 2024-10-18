package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.orderDto.request.CreateOrderRequestDto;
import com.jalil_be_app.madang_app.dto.orderDto.response.GetOrderByUserIdResponseDto;
import com.jalil_be_app.madang_app.dto.orderFacadeDto.request.ConfirmOrderRequestDto;
import com.jalil_be_app.madang_app.model.entity.Order;
import com.jalil_be_app.madang_app.service.OrderFacade;
import com.jalil_be_app.madang_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderFacade orderFacade;

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

    @PostMapping("confirm-order")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, Object>> confirm(
            @RequestHeader("Authorization") String token,
            @RequestBody ConfirmOrderRequestDto confirmOrderRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", orderFacade.confirm(token, confirmOrderRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all-order")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, Object>> getAll(){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", orderService.getAllOrderbyOrderId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
