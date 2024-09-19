package com.jalil_be_app.madang_app.dto.productDto.response;

import lombok.Data;

@Data
public class UpdateProductPriceResponseDto {
    private String name;
    private Double price;
    private String category;
    private String restaurantName;
}
