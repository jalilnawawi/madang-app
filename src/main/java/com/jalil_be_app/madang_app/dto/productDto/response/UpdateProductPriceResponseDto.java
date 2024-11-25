package com.jalil_be_app.madang_app.dto.productDto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateProductPriceResponseDto {
    @Schema(example = "Kopi Susu Gula Aren")
    private String name;
    @Schema(example = "18000")
    private Double price;
    @Schema(example = "DRINK")
    private String category;
    @Schema(example = "Almamater Coffee")
    private String restaurantName;
}
