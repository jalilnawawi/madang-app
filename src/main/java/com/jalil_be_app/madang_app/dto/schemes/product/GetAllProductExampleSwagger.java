package com.jalil_be_app.madang_app.dto.schemes.product;

import com.jalil_be_app.madang_app.dto.productDto.response.GetAllProductResponseDto;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllProductExampleSwagger {
    @ArraySchema(schema = @Schema(anyOf = {GetAllProductResponseDto.class, GetAllProductResponse.class}))
    private List<GetAllProductResponse> data;
    @Schema(example = "success")
    private String message;
}
