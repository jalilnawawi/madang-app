package com.jalil_be_app.madang_app.dto.orderItemDto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateQtyOrderItemResponseDto {
    @Schema(example = "417d4023-69da-4227-9b6a-9628bdd44dda")
    private UUID orderItemId;
    @Schema(example = "e5186e14-18ba-4868-bf05-8c0e4e61dca2")
    private UUID orderId;
    @Schema(example = "Espresso Coffee")
    private String productName;
    @Schema(example = "18000")
    private Double price;
    @Schema(example = "3")
    private int quantity;
    @Schema(example = "54000")
    private Double totalPrice;
    private UUID imageId;
    private String imageLink;
}
