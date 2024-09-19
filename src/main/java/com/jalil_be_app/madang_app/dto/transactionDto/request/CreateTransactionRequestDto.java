package com.jalil_be_app.madang_app.dto.transactionDto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTransactionRequestDto {
    private UUID productId;
    private String seatCategory;
    private String paymentMethod;
}
