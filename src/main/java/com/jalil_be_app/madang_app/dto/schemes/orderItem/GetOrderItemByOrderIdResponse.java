package com.jalil_be_app.madang_app.dto.schemes.orderItem;

import com.jalil_be_app.madang_app.dto.orderItemDto.response.GetOrderItemResponseDto;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderItemByOrderIdResponse {
    @Schema(example = "Arsa Coffee 2")
    private String restaurantName;
    @Schema(example = "Americano Coffee")
    private String productName;
    @Schema(example = "20000")
    private double productPrice;
    @Schema(example = "1")
    private int quantity;
    @Schema(example = "20000")
    private double totalPrice;
}
