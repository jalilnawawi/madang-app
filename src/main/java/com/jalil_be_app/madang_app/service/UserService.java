package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.userDto.RegisterUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.RegisterUserResponseDto;

public interface UserService {
    RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto);
}
