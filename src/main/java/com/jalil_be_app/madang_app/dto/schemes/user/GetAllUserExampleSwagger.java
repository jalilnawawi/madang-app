package com.jalil_be_app.madang_app.dto.schemes.user;

import com.jalil_be_app.madang_app.dto.userDto.GetAllUserResponseDto;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllUserExampleSwagger {
    @ArraySchema(schema = @Schema(anyOf = { GetAllUserResponseDto.class, GetAllUserResponse.class }))
    private List<GetAllUserResponseDto> data;
    @Schema(name = "message", example = "success")
    private String message;
}
