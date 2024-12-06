package com.jalil_be_app.madang_app.dto.schemes.restaurant;

import com.jalil_be_app.madang_app.dto.restaurantDto.response.CreateRestaurantResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantExampleSwagger {
    private CreateRestaurantResponseDto data;
    @Schema(name = "message", example = "success")
    private String message;
}
