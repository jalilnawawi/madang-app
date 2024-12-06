package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.orderItemDto.request.CreateOrderItemRequestDto;
import com.jalil_be_app.madang_app.dto.orderItemDto.request.UpdateQtyOrderItemRequestDto;
import com.jalil_be_app.madang_app.service.OrderItemService;
import com.jalil_be_app.madang_app.utils.ApiResponseAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/order-item")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponseAnnotations.CreateOrderItemResponses
    public ResponseEntity<Map<String, Object>> createOrderItem(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateOrderItemRequestDto createOrderItemRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", orderItemService.createOrderItem(token, createOrderItemRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("update-qty/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponseAnnotations.UpdateOrderItemQtyResponses
    public ResponseEntity<Map<String, Object>> updateQtyOrderItem(
            @PathVariable("id") UUID orderItemId,
            @RequestBody UpdateQtyOrderItemRequestDto updateQtyOrderItemRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", orderItemService.updateQtyOrderItem(orderItemId, updateQtyOrderItemRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all-orderItem")
//    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponseAnnotations.GetAllOrderItemResponses
    public ResponseEntity<Map<String, Object>> getAllOrderItem(){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", orderItemService.getAllOrderItem());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-orderItem-by-orderId/{id}")
//    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponseAnnotations.GetOrderItemByOrderIdResponses
    public ResponseEntity<Map<String, Object>> getListOrderItemByOrderId(
            @PathVariable("id") UUID orderId
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", orderItemService.getAllbyOrderId(orderId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
