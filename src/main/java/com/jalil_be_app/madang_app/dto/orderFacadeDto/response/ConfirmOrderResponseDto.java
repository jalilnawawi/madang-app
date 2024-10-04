package com.jalil_be_app.madang_app.dto.orderFacadeDto.response;

import com.jalil_be_app.madang_app.model.enums.PaymentMethod;
import lombok.Data;

@Data
public class ConfirmOrderResponseDto {
    private String customerName;
    private String restaurantName;
    private Double totalPrice;
    private PaymentMethod paymentMethod;
    private boolean completed;
}
