package com.jalil_be_app.madang_app.dto.userDto.register.response;

import com.jalil_be_app.madang_app.model.entity.account.Role;
import com.jalil_be_app.madang_app.model.entity.account.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserResponseDto {
    private String fullname;
    private String gender;
    private String username;
    private String email;
    private Set<Role> role;
    private String imageLink;
}
