package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.roleDto.request.CreateRoleRequestDto;
import com.jalil_be_app.madang_app.dto.roleDto.response.CreateRoleResponseDto;

public interface RoleService {
    CreateRoleResponseDto add(CreateRoleRequestDto createRoleRequestDto);
}
