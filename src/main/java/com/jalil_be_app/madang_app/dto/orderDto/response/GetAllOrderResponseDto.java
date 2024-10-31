package com.jalil_be_app.madang_app.dto.orderDto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllOrderResponseDto {
    private UUID orderId;
    private String username;
    private String restaurantName;
    private double totalPrice;
    private boolean completed;
}
