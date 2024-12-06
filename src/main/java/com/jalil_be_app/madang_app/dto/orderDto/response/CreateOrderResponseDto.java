package com.jalil_be_app.madang_app.dto.orderDto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderResponseDto {
    private UUID orderId;
    private UUID userId;
    @Schema(example = "user1")
    private String username;
    private UUID restaurantId;
    @Schema(example = "Arsa Coffee 2")
    private String restaurantName;
    @Schema(example = "false")
    private boolean completed;
}
