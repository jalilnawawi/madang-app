package com.jalil_be_app.madang_app.dto.userDto;

import com.jalil_be_app.madang_app.model.entity.account.Role;
import com.jalil_be_app.madang_app.model.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Data
public class GetAllUserResponseDto {
    private String fullname;
    private Gender gender;
    private String username;
    private String email;
    private Set<Role> role;
    private String imageLink;

    public GetAllUserResponseDto(String fullname, Gender gender, String username, String email, Set<Role> role, String imageLink) {
        this.fullname = fullname;
        this.gender = gender;
        this.username = username;
        this.email = email;
        this.role = role;
        this.imageLink = imageLink;
    }
}
