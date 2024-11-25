package com.jalil_be_app.madang_app.dto.schemes.order;

import com.jalil_be_app.madang_app.dto.orderDto.response.GetAllOrderResponseDto;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllOrderExampleSwagger {
    @ArraySchema(schema = @Schema(anyOf = {GetAllOrderResponseDto.class, GetAllOrderResponse.class}))
    private List<GetAllOrderResponse> data;
    @Schema(example = "success")
    private String message;
}
