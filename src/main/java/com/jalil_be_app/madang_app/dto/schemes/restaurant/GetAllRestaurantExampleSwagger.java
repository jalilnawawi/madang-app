package com.jalil_be_app.madang_app.dto.schemes.restaurant;

import com.jalil_be_app.madang_app.dto.restaurantDto.response.GetAllRestaurantResponseDto;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllRestaurantExampleSwagger {
    @ArraySchema(schema = @Schema(anyOf = {GetAllRestaurantResponseDto.class, GetAllRestaurantResponse.class}))
    private List<GetAllRestaurantResponseDto> data;
    @Schema(name = "message", example = "success")
    private String message;
}
