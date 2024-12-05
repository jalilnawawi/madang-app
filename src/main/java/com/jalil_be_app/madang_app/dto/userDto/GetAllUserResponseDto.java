package com.jalil_be_app.madang_app.dto.userDto;

import com.jalil_be_app.madang_app.model.entity.account.Role;
import com.jalil_be_app.madang_app.model.enums.Gender;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponseDto {
    private UUID userId;
    private String fullname;
    private Gender gender;
    private String username;
    private String email;
    private Set<Role> role;
    private String imageLink;
}
