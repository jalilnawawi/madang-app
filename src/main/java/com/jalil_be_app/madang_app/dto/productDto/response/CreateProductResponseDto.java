package com.jalil_be_app.madang_app.dto.productDto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateProductResponseDto {
    private UUID productId;
    @Schema(example = "Espresso Coffee")
    private String productName;
    @Schema(example = "15000")
    private Double price;
    @Schema(example = "DRINK")
    private String category;
    @Schema(example = "espresso_image")
    private String imageLink;
    @Schema(example = "Arsa Coffee")
    private String restaurantName;
    private UUID restaurantId;
}
