package com.jalil_be_app.madang_app.dto.orderItemDto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllOrderItemResponseDto {
    private UUID orderItemId;
    private String restaurantName;
    private String productName;
    private double productPrice;
    private int quantity;
    private double totalPrice;
}
