package com.jalil_be_app.madang_app.dto.userDto.register.request;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserRequestDto {
    @Schema(name = "fullname", example = "Gibran Rakabuming Raka")
    private String fullname;
    @Schema(name = "gender", example = "Laki Laki")
    private String gender;
    @Schema(name = "username", example = "gibranrk")
    private String username;
    @Schema(name = "email", example = "gibranraka@gmail.com")
    private String email;
    @Schema(name = "password", example = "gibran123")
    private String password;
    @ArraySchema(schema = @Schema(example = "user"))
    private Set<String> role;
    @Schema(name = "imageLink", example = "gibran_image")
    private String imageLink;
}
