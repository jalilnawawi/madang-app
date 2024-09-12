package com.jalil_be_app.madang_app.dto.roleDto;

import com.jalil_be_app.madang_app.model.entity.account.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateRoleResponseDto {
    private UUID roleId;
    private UserRole roleName;
}
