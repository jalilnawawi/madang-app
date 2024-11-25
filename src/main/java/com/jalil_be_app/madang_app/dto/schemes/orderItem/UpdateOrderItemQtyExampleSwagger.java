package com.jalil_be_app.madang_app.dto.schemes.orderItem;

import com.jalil_be_app.madang_app.dto.orderItemDto.response.UpdateQtyOrderItemResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrderItemQtyExampleSwagger {
    private UpdateQtyOrderItemResponseDto data;
    @Schema(example = "success")
    private String message;
}
