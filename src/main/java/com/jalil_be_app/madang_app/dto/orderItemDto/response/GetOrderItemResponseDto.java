package com.jalil_be_app.madang_app.dto.orderItemDto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class GetOrderItemResponseDto {
    private String restaurantName;
    private String productName;
    private double productPrice;
    private int quantity;
    private double totalPrice;

    public GetOrderItemResponseDto(String restaurantName, String productName, double productPrice, int quantity, double totalPrice) {
        this.restaurantName = restaurantName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
