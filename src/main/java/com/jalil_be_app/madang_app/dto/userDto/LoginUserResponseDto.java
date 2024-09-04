package com.jalil_be_app.madang_app.dto.userDto;

import com.jalil_be_app.madang_app.model.enums.UserStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Setter
@Getter
public class LoginUserResponseDto {
    private String accessToken;
    private UUID userId;
    private UserStatus userStatus;
}
