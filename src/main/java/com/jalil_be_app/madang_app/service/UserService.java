package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.userDto.GetAllUserResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.GetUserResponseDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserService {
    RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto, MultipartFile file) throws IOException;
    LoginUserResponseDto login(LoginUserRequestDto loginUserRequestDto);
    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
    UpdatePasswordResponseDto updatePassword(String token, UpdatePasswordRequestDto updatePasswordRequestDto);
    UpdateImageResponseDto updateImage(String token, UpdateImageRequestDto updateImageRequestDto);
    List<GetAllUserResponseDto> getAllUser();
    GetUserResponseDto getUserByToken(String token);
    GetUserResponseDto getUserById(UUID userId);
}
