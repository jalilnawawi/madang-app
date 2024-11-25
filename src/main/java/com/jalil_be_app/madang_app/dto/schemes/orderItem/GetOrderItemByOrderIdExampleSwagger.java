package com.jalil_be_app.madang_app.dto.schemes.orderItem;

import com.jalil_be_app.madang_app.dto.orderItemDto.response.GetOrderItemResponseDto;
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
public class GetOrderItemByOrderIdExampleSwagger {
    @ArraySchema(schema = @Schema(anyOf = {GetOrderItemResponseDto.class, GetOrderItemByOrderIdResponse.class}))
    private List<GetOrderItemResponseDto> data;
    @Schema(example = "success")
    private String message;
}
