package com.jalil_be_app.madang_app.dto.userDto.login.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginUserRequestDto {
    @Schema(name = "email", example = "user1@gmail.com")
    private String email;
    @Schema(name = "password", example = "password123")
    private String password;
}
