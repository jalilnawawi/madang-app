package com.jalil_be_app.madang_app.dto.restaurantDto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateRestaurantResponseDto {
    private UUID restaurantId;
    private UUID userId;
    @Schema(example = "Ayam Geprek Kumlot")
    private String restaurantName;
    @Schema(example = "Ayam Geprek murah + Free refill nasi")
    private String description;
    @Schema(example = "Ngoresan, Jebres, Surakarta")
    private String address;
    @Schema(example = "Warung Makan")
    private String category;
    private UUID imageId;
    @Schema(example = "geprekKumlot_image")
    private String imageLink;
}
