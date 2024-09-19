package com.jalil_be_app.madang_app.dto.transactionDto.request;

import com.jalil_be_app.madang_app.dto.seatDto.CheckSeatReq;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateTransactionRequestDto {
    private UUID productId;
    private CheckSeatReq seat;
    private String paymentMethod;
}
