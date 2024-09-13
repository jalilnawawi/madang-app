package com.jalil_be_app.madang_app.dto.userDto.login;

import com.jalil_be_app.madang_app.model.entity.account.Role;
import com.jalil_be_app.madang_app.model.enums.UserStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
public class LoginUserResponseDto {
    private String accessToken;
    private String refreshToken;
    private UUID userId;
    private UserStatus userStatus;
    private List<String> roles;
}
