package com.jalil_be_app.madang_app.dto.userDto.register;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserRequestDto {
    private String fullname;
    private String gender;
    private String username;
    private String email;
    private String password;
    private Set<String> role;
    private String imageLink;
}
