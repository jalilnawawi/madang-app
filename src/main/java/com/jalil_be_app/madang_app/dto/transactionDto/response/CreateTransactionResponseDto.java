package com.jalil_be_app.madang_app.dto.transactionDto.response;

import lombok.Data;

@Data
public class CreateTransactionResponseDto {
    private String transactionNumber;
    private String username;
    private String productName;
    private String seatName;
    private Double totalPrice;
    private String paymentMethod;
}
