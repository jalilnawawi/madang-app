package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.transactionDto.request.CreateTransactionRequestDto;
import com.jalil_be_app.madang_app.dto.transactionDto.response.CreateTransactionResponseDto;
import com.jalil_be_app.madang_app.model.enums.PaymentMethod;

public interface TransactionService {
    CreateTransactionResponseDto create(String token, CreateTransactionRequestDto createTransactionRequestDto);
    PaymentMethod selectPaymentMethod(String paymentName);
}
