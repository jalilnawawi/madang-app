package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.roleDto.CreateRoleRequestDto;
import com.jalil_be_app.madang_app.dto.roleDto.CreateRoleResponseDto;
import com.jalil_be_app.madang_app.model.entity.account.Role;

public interface RoleService {
    CreateRoleResponseDto add(CreateRoleRequestDto createRoleRequestDto);
}
