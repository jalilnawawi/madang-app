package com.jalil_be_app.madang_app.exception;

import com.jalil_be_app.madang_app.dto.base.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandlers {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponse<String>> handleCustomException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(BaseResponse.failure(exception.getStatusCode().value(), exception.getReason()));
    }
}
