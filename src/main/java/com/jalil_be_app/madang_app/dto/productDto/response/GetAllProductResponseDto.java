package com.jalil_be_app.madang_app.dto.productDto.response;

import com.jalil_be_app.madang_app.model.enums.ProductCategory;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllProductResponseDto {
    private UUID id;
    private String name;
    private Double price;
    private ProductCategory categoryName;
    private String imageLink;
    private Float rating;
    private String restaurantName;
}
