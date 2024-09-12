package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.userDto.login.LoginUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.login.RefreshTokenRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.register.RegisterUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updateImage.UpdateImageRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updatePassword.UpdatePasswordRequestDto;
import com.jalil_be_app.madang_app.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<Map<String, Object>> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", userService.refreshToken(refreshTokenRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update-profile/password")
    public ResponseEntity<Map<String, Object>> updatePassword(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdatePasswordRequestDto updatePasswordRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Update password is success!");
        response.put("data", userService.updatePassword(token, updatePasswordRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update-profile/image")
    public ResponseEntity<Map<String, Object>> updateImage(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateImageRequestDto updateImageRequestDto
            ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Update image is success!");
        response.put("data", userService.updateImage(token, updateImageRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String hello(){
        return "hello world";
    }
}
