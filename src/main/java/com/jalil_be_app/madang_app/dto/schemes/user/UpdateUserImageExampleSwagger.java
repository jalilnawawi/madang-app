package com.jalil_be_app.madang_app.dto.schemes.user;

import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updateImage.response.UpdateImageResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updatePassword.response.UpdatePasswordResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserImageExampleSwagger {
    private UpdateImageResponseDto data;
    @Schema(name = "message", example = "success")
    private String message;
}
