package com.jalil_be_app.madang_app.dto.schemes.product;

import com.jalil_be_app.madang_app.model.enums.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllProductResponse {
    private UUID id;
    @Schema(example = "Americano Coffee")
    private String name;
    @Schema(example = "20000")
    private Double price;
    private ProductCategory categoryName;
    @Schema(example = "americano_image")
    private String imageLink;
    private Float rating;
    @Schema(example = "East Side Coffee")
    private String restaurantName;
}
