package com.jalil_be_app.madang_app.dto.schemes.product;

import com.jalil_be_app.madang_app.dto.productDto.response.GetAllProductByRestoIdResponseDto;
import com.jalil_be_app.madang_app.model.enums.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllProductByRestoIdResponseDtoExampleSwagger {
    private GetAllProductByRestoIdResponseDto data;
    @Schema(example = "success")
    private String message;
}
