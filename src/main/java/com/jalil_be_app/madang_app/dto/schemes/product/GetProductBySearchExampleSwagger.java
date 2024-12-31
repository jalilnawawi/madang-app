package com.jalil_be_app.madang_app.dto.schemes.product;

import com.jalil_be_app.madang_app.dto.productDto.response.GetAllProductResponseDto;
import com.jalil_be_app.madang_app.dto.productDto.response.GetProductBySearchResponseDto;
import com.jalil_be_app.madang_app.model.enums.ProductCategory;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductBySearchExampleSwagger {
    @ArraySchema(schema = @Schema(anyOf = {GetProductBySearchResponseDto.class, GetProductBySearchResponseDto.class}))
    private List<GetProductBySearchResponseDto> data;
    @Schema(example = "success")
    private String message;
}
