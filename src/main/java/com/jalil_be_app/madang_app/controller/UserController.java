package com.jalil_be_app.madang_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jalil_be_app.madang_app.dto.userDto.GetAllUserResponseDto;
import com.jalil_be_app.madang_app.dto.userDto.login.request.LoginUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.login.request.RefreshTokenRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.register.request.RegisterUserRequestDto;
import com.jalil_be_app.madang_app.dto.userDto.updateProfile.updatePassword.request.UpdatePasswordRequestDto;
import com.jalil_be_app.madang_app.service.UserService;
import com.jalil_be_app.madang_app.utils.ApiResponseAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/auth/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiResponseAnnotations.RegisterUserApiResponses
    public ResponseEntity<Map<String, Object>> register(
            @RequestPart("data") String registerUserDto,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        RegisterUserRequestDto registerUserRequestDto = convertToString(registerUserDto);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", userService.register(registerUserRequestDto, file));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private RegisterUserRequestDto convertToString(String registerUserDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(registerUserDto, RegisterUserRequestDto.class);
    }

    @PostMapping("/auth/login")
    @ApiResponseAnnotations.LoginUserApiResponses
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUserRequestDto loginUserRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", userService.login(loginUserRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/auth/refresh-token")
    @ApiResponseAnnotations.RefreshTokenApiResponses
    public ResponseEntity<Map<String, Object>> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", userService.refreshToken(refreshTokenRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/update-profile/password/{userId}")
    @ApiResponseAnnotations.UpdatePasswordApiResponses
    public ResponseEntity<Map<String, Object>> updatePassword(
            @PathVariable("userId") UUID userId,
            @RequestBody UpdatePasswordRequestDto updatePasswordRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Update password is success!");
        response.put("data", userService.updatePassword(userId, updatePasswordRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "/update-profile/image/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponseAnnotations.UpdateUserImageApiResponses
    public ResponseEntity<Map<String, Object>> updateImage(
            @PathVariable("userId") UUID userId,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Update image is success!");
        response.put("data", userService.updateImage(userId, file));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all-user")
    @ApiResponseAnnotations.GetAllUserApiResponses
    public List<GetAllUserResponseDto> getAllUser(){
        return userService.getAllUser();
    }

//    @GetMapping("/get-user-by-token")
////    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<Map<String, Object>> getUserByToken(@RequestHeader("Authorization") String token){
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "success");
//        response.put("data", userService.getUserByToken(token));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping("/get-user-by-id/{id}")
//    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponseAnnotations.GetUserByIdResponses
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable("id") UUID id){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", userService.getUserById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
