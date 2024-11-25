package com.jalil_be_app.madang_app.dto.productDto.response;

import com.jalil_be_app.madang_app.model.enums.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllProductResponseDto {
    private UUID id;
    @Schema(example = "Espresso Coffee")
    private String name;
    @Schema(example = "18000")
    private Double price;
    private ProductCategory categoryName;
    @Schema(example = "espresso_image")
    private String imageLink;
    private Float rating;
    @Schema(example = "Arsa Coffee 2")
    private String restaurantName;
}
