package com.jalil_be_app.madang_app.dto.productDto;

import lombok.Data;

@Data
public class CreateProductRequestDto {
    private String name;
    private Double price;
    private String category;
    private String imageLink;
}
