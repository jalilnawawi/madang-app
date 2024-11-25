package com.jalil_be_app.madang_app.dto.orderItemDto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetOrderItemResponseDto {
    @Schema(example = "Arsa Coffee 2")
    private String restaurantName;
    @Schema(example = "Espresso Coffee")
    private String productName;
    @Schema(example = "18000")
    private double productPrice;
    @Schema(example = "2")
    private int quantity;
    @Schema(example = "36000")
    private double totalPrice;
}
