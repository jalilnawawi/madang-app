package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.userDto.login.request.LoginUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.login.response.LoginUserResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.login.request.RefreshTokenRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.login.response.RefreshTokenResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.register.request.RegisterUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.register.response.RegisterUserResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updateImage.request.UpdateImageRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updateImage.response.UpdateImageResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updatePassword.request.UpdatePasswordRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updatePassword.response.UpdatePasswordResponseDto;

public interface UserService {
    RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto);
    LoginUserResponseDto login(LoginUserRequestDto loginUserRequestDto);
    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
    UpdatePasswordResponseDto updatePassword(String token, UpdatePasswordRequestDto updatePasswordRequestDto);
    UpdateImageResponseDto updateImage(String token, UpdateImageRequestDto updateImageRequestDto);
}
