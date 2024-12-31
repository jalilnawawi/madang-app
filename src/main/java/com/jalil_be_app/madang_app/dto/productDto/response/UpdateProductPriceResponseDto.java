package com.jalil_be_app.madang_app.dto.productDto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateProductPriceResponseDto {
    private UUID productId;
    @Schema(example = "Kopi Susu Gula Aren")
    private String name;
    @Schema(example = "18000")
    private Double price;
    @Schema(example = "DRINK")
    private String category;
    private UUID restaurantId;
    @Schema(example = "Almamater Coffee")
    private String restaurantName;
}
