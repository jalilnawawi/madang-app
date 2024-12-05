package com.jalil_be_app.madang_app.dto.schemes.restaurant;

import com.jalil_be_app.madang_app.dto.restaurantDto.response.GetAllRestaurantResponseDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.response.GetRestaurantResponseDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.enums.RestaurantCategory;
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
public class GetRestaurantByIdExampleSwagger {
    private GetRestaurantResponseDto data;
    @Schema(name = "message", example = "success")
    private String message;
}
