package com.jalil_be_app.madang_app.dto.userDto;

import com.jalil_be_app.madang_app.model.entity.Image;
import lombok.Data;

@Data
public class RegisterUserResponseDto {
    private String fullname;
    private String gender;
    private String username;
    private String email;
//    private String imageLink;
}
