package com.jalil_be_app.madang_app.utils;


import com.jalil_be_app.madang_app.dto.schemes.user.LoginUserExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.user.RegisterUserExampleSwagger;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

public class ApiResponseAnnotations {

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RegisterUserExampleSwagger.class)
                )
            }
        ),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = {
                @Content(
                        mediaType = "application/json",
                        examples = {
                            @ExampleObject(
                                name = "Password Not Match",
                                value = "{\"data\": null, \"message\": \"Password not matched\"}"
                            )
                        }
                )
            }
        )
    })
    public @interface RegisterUserApiResponses{
    }


    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginUserExampleSwagger.class)
                    )
                }
            ),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                content = {
                    @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Email or Password invalid",
                                            value = "{\"data\": null, \"message\" : \"Your email or password is invalid\"}"
                                    )
                            }
                    )
                }
            )
    })
    public @interface LoginUserApiResponses {
    }


    public @interface RefreshTokenApiResponses {
    }
}
