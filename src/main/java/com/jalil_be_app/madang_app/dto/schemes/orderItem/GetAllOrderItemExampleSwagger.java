package com.jalil_be_app.madang_app.dto.schemes.orderItem;

import com.jalil_be_app.madang_app.dto.orderItemDto.response.GetAllOrderItemResponseDto;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllOrderItemExampleSwagger {
    @ArraySchema(schema = @Schema(anyOf = {GetAllOrderItemResponseDto.class, GetAllOrderItemResponse.class}))
    private List<GetAllOrderItemResponse> data;
    @Schema(example = "success")
    private String message;
}
