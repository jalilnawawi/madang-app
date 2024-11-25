package com.jalil_be_app.madang_app.dto.orderDto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderRequestDto {
    @Schema(example = "1582502f-071c-446b-a575-ccae8e1fa398")
    private String restaurantId;
}
