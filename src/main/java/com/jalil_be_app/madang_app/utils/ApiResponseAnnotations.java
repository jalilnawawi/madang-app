package com.jalil_be_app.madang_app.utils;


import com.jalil_be_app.madang_app.dto.schemes.restaurant.GetAllRestaurantExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.user.*;
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

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RefreshTokenExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Refresh token invalid",
                                                    value = "{\"data\": null, \"message\" : \"Your token is invalid\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface RefreshTokenApiResponses {
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UpdatePasswordExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Failed update password because user not found",
                                                    value = "{\"data\": null, \"message\" : \"User not found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface UpdatePasswordApiResponses {
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UpdateUserImageExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Failed update image because user not found",
                                                    value = "{\"data\": null, \"message\" : \"User not found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface UpdateUserImageApiResponses {
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetAllUserExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Failed get user because token is invalid",
                                                    value = "{\"data\": null, \"message\" : \"User is unauthorized\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface GetAllUserApiResponses {
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetUserByIdExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Failed get user because user not found",
                                                    value = "{\"data\": null, \"message\" : \"User not found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface GetUserByIdResponses {
    }

    // Annotations for Restaurant in below
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetAllRestaurantExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "unauthorized",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Failed get all restaurant because token is invalid",
                                                    value = "{\"data\": null, \"message\" : \"User is unauthorized\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface GetAllRestaurantResponses{
    }
}
