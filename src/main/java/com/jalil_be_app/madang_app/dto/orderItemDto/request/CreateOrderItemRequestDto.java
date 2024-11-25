package com.jalil_be_app.madang_app.dto.orderItemDto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateOrderItemRequestDto {
    @Schema(example = "a9448950-7d2d-4d07-8177-4fa5986900b8")
    private String orderId;
    @Schema(example = "c7b8b5b6-8adf-4ea7-9d97-538000109a15")
    private String productId;
    @Schema(example = "2")
    private int quantity;
}
