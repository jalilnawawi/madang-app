package com.jalil_be_app.madang_app.dto.userDto;

import com.jalil_be_app.madang_app.model.entity.account.Role;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class GetUserResponseDto {
    private UUID userId;
    private String fullname;
    private String gender;
    private String username;
    private String email;
    private Set<Role> role;
    private UUID imageId;
    private String imageLink;
}
