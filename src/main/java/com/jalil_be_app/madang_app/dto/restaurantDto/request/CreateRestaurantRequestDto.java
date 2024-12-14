package com.jalil_be_app.madang_app.dto.restaurantDto.request;

import com.jalil_be_app.madang_app.model.enums.RestaurantCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateRestaurantRequestDto {
    @Schema(example = "Ayam Geprek Kumlot")
    private String name;
    @Schema(example = "Ayam Geprek murah + Free refill nasi")
    private String description;
    @Schema(example = "Ngoresan, Jebres, Surakarta")
    private String address;
    @Schema(example = "Warung Makan")
    private String category;
}
