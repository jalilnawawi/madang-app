package com.jalil_be_app.madang_app.dto.userDto.updateProfile.updateImage.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateImageResponseDto {
    private UUID userId;
    private String username;
    private UUID imageId;
    private String imageLink;
}
