package com.jalil_be_app.madang_app.dto.orderDto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllOrderResponseDto {
    private UUID orderId;
    @Schema(example = "user2")
    private String username;
    @Schema(example = "East Side Coffee")
    private String restaurantName;
    @Schema(example = "50000")
    private double totalPrice;
    @Schema(example = "true")
    private boolean completed;
}
