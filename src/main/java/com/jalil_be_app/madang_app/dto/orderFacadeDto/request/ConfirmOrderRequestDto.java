package com.jalil_be_app.madang_app.dto.orderFacadeDto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ConfirmOrderRequestDto {
    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String orderId;
    @Schema(example = "Cash")
    private String paymentMethod;
}
