package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.roleDto.request.CreateRoleRequestDto;
import com.jalil_be_app.madang_app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> add(@RequestBody CreateRoleRequestDto createRoleRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", roleService.add(createRoleRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
