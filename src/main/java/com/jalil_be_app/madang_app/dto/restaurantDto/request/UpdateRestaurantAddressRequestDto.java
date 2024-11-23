package com.jalil_be_app.madang_app.dto.restaurantDto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateRestaurantAddressRequestDto {
    @Schema(example = "Jl. Slamet Riyadi")
    private String address;
}
