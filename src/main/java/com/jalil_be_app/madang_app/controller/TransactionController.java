package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.transactionDto.request.CreateTransactionRequestDto;
import com.jalil_be_app.madang_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, Object>> create(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateTransactionRequestDto createTransactionRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", transactionService.create(token, createTransactionRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}