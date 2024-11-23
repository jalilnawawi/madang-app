package com.jalil_be_app.madang_app.dto.restaurantDto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateRestaurantResponseDto {
    @Schema(example = "Ayam Geprek Kumlot")
    private String name;
    @Schema(example = "Ayam Geprek murah + Free refill nasi")
    private String description;
    @Schema(example = "Ngoresan, Jebres, Surakarta")
    private String address;
    @Schema(example = "Warung Makan")
    private String category;
    @Schema(example = "geprekKumlot_image")
    private String imageLink;
}
