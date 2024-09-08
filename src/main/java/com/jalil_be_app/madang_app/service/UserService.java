package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.userDto.login.LoginUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.login.LoginUserResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.login.RefreshTokenRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.login.RefreshTokenResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.register.RegisterUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.register.RegisterUserResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updateImage.UpdateImageRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updateImage.UpdateImageResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updatePassword.UpdatePasswordRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updatePassword.UpdatePasswordResponseDto;

public interface UserService {
    RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto);
    LoginUserResponseDto login(LoginUserRequestDto loginUserRequestDto);
    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
    UpdatePasswordResponseDto updatePassword(String token, UpdatePasswordRequestDto updatePasswordRequestDto);
    UpdateImageResponseDto updateImage(String token, UpdateImageRequestDto updateImageRequestDto);
}
