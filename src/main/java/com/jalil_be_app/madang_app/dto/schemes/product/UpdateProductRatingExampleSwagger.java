package com.jalil_be_app.madang_app.dto.schemes.product;

import com.jalil_be_app.madang_app.dto.productDto.response.UpdateProductPriceResponseDto;
import com.jalil_be_app.madang_app.dto.productDto.response.UpdateProductRatingResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRatingExampleSwagger {
    private UpdateProductRatingResponseDto data;
    @Schema(example = "success")
    private String message;
}
