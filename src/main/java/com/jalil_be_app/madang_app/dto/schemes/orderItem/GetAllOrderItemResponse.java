package com.jalil_be_app.madang_app.dto.schemes.orderItem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllOrderItemResponse {
    @Schema(example = "6e4b5f99-29c5-4004-ab89-cc47d7979ec1")
    private UUID orderItemId;
    @Schema(example = "East Side Coffee")
    private String restaurantName;
    @Schema(example = "Americano Coffee")
    private String productName;
    @Schema(example = "20000")
    private double productPrice;
    @Schema(example = "2")
    private int quantity;
    @Schema(example = "40000")
    private double totalPrice;
}
