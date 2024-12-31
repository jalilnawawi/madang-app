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
public class GetProductBySearchResponseDto {
    private UUID productId;
    @Schema(example = "Espresso Coffee")
    private String productName;
    @Schema(example = "18000")
    private Double price;
    private ProductCategory categoryName;
    private UUID imageId;
    private String imageLink;
    private Float rating;
    private UUID restaurantId;
    @Schema(example = "Arsa Coffee 2")
    private String restaurantName;
    private UUID userId;
    private String searchQuery;
}
