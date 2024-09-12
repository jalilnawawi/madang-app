package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.roleDto.CreateRoleRequestDto;
import com.jalil_be_app.madang_app.dto.roleDto.CreateRoleResponseDto;
import com.jalil_be_app.madang_app.model.entity.account.Role;
import com.jalil_be_app.madang_app.model.entity.account.UserRole;
import com.jalil_be_app.madang_app.repository.RoleRepository;
import com.jalil_be_app.madang_app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public CreateRoleResponseDto add(CreateRoleRequestDto createRoleRequestDto) {
        Role role = new Role();
        if (createRoleRequestDto.getName().equalsIgnoreCase("user")){
            role.setName(UserRole.ROLE_USER);
        } else if (createRoleRequestDto.getName().equalsIgnoreCase("merchant")) {
            role.setName(UserRole.ROLE_MERCHANT);
        } else if (createRoleRequestDto.getName().equalsIgnoreCase("admin")) {
            role.setName(UserRole.ROLE_ADMIN);
        }
        roleRepository.save(role);

        CreateRoleResponseDto responseDto = new CreateRoleResponseDto();
        responseDto.setRoleId(role.getId());
        responseDto.setRoleName(role.getName());
        return responseDto;
    }
}
