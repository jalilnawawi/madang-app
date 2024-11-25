package com.jalil_be_app.madang_app.dto.schemes.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllOrderResponse {
    private UUID orderId;
    @Schema(example = "user1")
    private String username;
    @Schema(example = "Arsa Coffee 2")
    private String restaurantName;
    @Schema(example = "25000")
    private double totalPrice;
    @Schema(example = "false")
    private boolean completed;
}
