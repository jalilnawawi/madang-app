package com.jalil_be_app.madang_app.dto.schemes.product;

import com.jalil_be_app.madang_app.dto.productDto.response.CreateProductResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductExampleSwagger {
    private CreateProductResponseDto data;
    @Schema(example = "success")
    private String message;
}
