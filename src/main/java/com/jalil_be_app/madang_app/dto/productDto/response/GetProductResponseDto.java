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
public class GetProductResponseDto {
    private UUID productId;
    @Schema(example = "Kopi Susu Gula Aren")
    private String productName;
    @Schema(example = "15000")
    private Double price;
    @Schema(example = "DRINK")
    private ProductCategory categoryName;
    private UUID imageId;
    private String imageLink;
    private Float rating;
    private UUID restaurantId;
    @Schema(example = "Almamater Coffee")
    private String restaurantName;
    private UUID userId;
}
