package com.jalil_be_app.madang_app.dto.schemes.order;

import com.jalil_be_app.madang_app.dto.orderDto.response.CreateOrderResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderExampleSwagger {
    private CreateOrderResponseDto data;
    @Schema(example = "success")
    private String message;
}
