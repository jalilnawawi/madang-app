package com.jalil_be_app.madang_app.dto.productDto;

import lombok.Data;

@Data
public class CreateProductResponseDto {
    private String name;
    private Double price;
    private String category;
    private String imageLink;
    private String restaurantName;
}
