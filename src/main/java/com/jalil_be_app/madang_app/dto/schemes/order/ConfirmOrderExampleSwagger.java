package com.jalil_be_app.madang_app.dto.schemes.order;

import com.jalil_be_app.madang_app.dto.orderFacadeDto.response.ConfirmOrderResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmOrderExampleSwagger {
    private ConfirmOrderResponseDto data;
    @Schema(example = "success")
    private String message;
}
