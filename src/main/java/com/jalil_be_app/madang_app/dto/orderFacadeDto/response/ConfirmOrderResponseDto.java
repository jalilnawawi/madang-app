package com.jalil_be_app.madang_app.dto.orderFacadeDto.response;

import com.jalil_be_app.madang_app.model.enums.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ConfirmOrderResponseDto {
    @Schema(example = "Gibran")
    private String customerName;
    @Schema(example = "Arsa Coffee 2")
    private String restaurantName;
    @Schema(example = "50000")
    private Double totalPrice;
    @Schema(example = "Cash")
    private PaymentMethod paymentMethod;
    @Schema(example = "true")
    private boolean completed;
}
