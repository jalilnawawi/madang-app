package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.userDto.LoginUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.LoginUserResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.RegisterUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.RegisterUserResponseDto;
import com.jalil_be_app.madang_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterUserRequestDto registerUserRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", userService.register(registerUserRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUserRequestDto loginUserRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", userService.login(loginUserRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
