package com.jalil_be_app.madang_app.dto.schemes.product;

import com.jalil_be_app.madang_app.dto.productDto.response.GetAllProductByRestoIdResponseDto;
import com.jalil_be_app.madang_app.dto.productDto.response.GetAllProductResponseDto;
import com.jalil_be_app.madang_app.model.enums.ProductCategory;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllProductByRestoIdResponseDtoExampleSwagger {
    @ArraySchema(schema = @Schema(anyOf = {GetAllProductByRestoIdResponseDto.class, GetAllProductResponse.class}))
    private List<GetAllProductByRestoIdResponseDto> data;
    @Schema(example = "success")
    private String message;
}
