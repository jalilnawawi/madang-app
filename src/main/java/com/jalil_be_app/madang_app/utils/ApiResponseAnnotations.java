package com.jalil_be_app.madang_app.utils;


import com.jalil_be_app.madang_app.dto.schemes.order.ConfirmOrderExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.order.CreateOrderExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.order.GetAllOrderExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.order.GetOrderByIdExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.orderItem.GetAllOrderItemExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.orderItem.GetOrderItemByOrderIdExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.product.CreateProductExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.product.GetAllProductExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.product.GetProductByIdExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.product.UpdateProductPriceExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.restaurant.CreateRestaurantExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.restaurant.GetAllRestaurantExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.restaurant.GetRestaurantByIdExampleSwagger;
import com.jalil_be_app.madang_app.dto.schemes.restaurant.UpdateRestaurantAddressExampleSwagger;
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
        @ApiResponse(responseCode = "201", description = "Success Register for a new user",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RegisterUserExampleSwagger.class)
                )
            }
        ),
        @ApiResponse(responseCode = "400", description = "Password not Match",
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
            @ApiResponse(responseCode = "201", description = "User Login Success",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginUserExampleSwagger.class)
                    )
                }
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
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
            @ApiResponse(responseCode = "201", description = "Success get a new access token",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RefreshTokenExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Wrong Refresh Token",
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
            @ApiResponse(responseCode = "200", description = "Success Update User Password",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UpdatePasswordExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "User not found",
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
            @ApiResponse(responseCode = "200", description = "Success Update User Image",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UpdateUserImageExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "User Not Found",
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
            @ApiResponse(responseCode = "200", description = "Success Get All User Data",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetAllUserExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "User Not Authorized to Access Endpoint",
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
            @ApiResponse(responseCode = "200", description = "Success Get User by User Id",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetUserByIdExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "User Id Not Found",
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
            @ApiResponse(responseCode = "200", description = "Success Get All Restaurant Data",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetAllRestaurantExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "User Not Authorized to Access Endpoint",
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

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Get Restaurant by Restaurant Id",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetRestaurantByIdExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Restaurant Id Not Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Restaurant Not Found",
                                                    value = "{\"data\": null, \"message\" : \"Restaurant not found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface GetRestaurantByIdResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success Create New Restaurant",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CreateRestaurantExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "User not allowed to have two Restaurant",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Single Restaurant per User",
                                                    value = "{\"data\": null, \"message\" : \"User only can have 1 Restaurant\"}"
                                            )
                                    }
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Empty Restaurant Name",
                                                    value = "{\"data\": null, \"message\" : \"Restaurant name can't empty\"}"
                                            ),
                                            @ExampleObject(
                                                    name = "Empty Restaurant address",
                                                    value = "{\"data\": null, \"message\" : \"Restaurant address can't empty\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface CreateRestaurantResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Update Restaurant Address",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UpdateRestaurantAddressExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Restaurant Not Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Restaurant Not Found",
                                                    value = "{\"data\": null, \"message\" : \"Restaurant not found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface UpdateRestaurantAddressResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Delete Restaurant",
                    content = {
                            @Content(mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Delete Restaurant",
                                                    value = "{\"message\" : \"success\"}"
                                            )
                                    }
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Restaurant Not Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Restaurant Not Found",
                                                    value = "{\"data\": null, \"message\" : \"Restaurant not found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface DeleteRestaurantResponses{
    }

    // Annotations for Product in below
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success Create New Product",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CreateProductExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Empty Product Name",
                                                    value = "{\"data\": null, \"message\" : \"Product name can't empty\"}"
                                            ),
                                            @ExampleObject(
                                                    name = "Empty Product Price",
                                                    value = "{\"data\": null, \"message\" : \"Product price can't empty\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface CreateProductResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Get All Product",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetAllProductExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "User Not Authorized to Access Endpoint",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Unauthorized User",
                                                    value = "{\"data\": null, \"message\" : \"You are not authorized to access\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface GetAllProductResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Get Product",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetProductByIdExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Product Id Not Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Product Not Found",
                                                    value = "{\"data\": null, \"message\" : \"Product not found\"}"
                                            )
                                    }
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "User Not Authorized to Access Endpoint",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Unauthorized User",
                                                    value = "{\"data\": null, \"message\" : \"You are not authorized to access\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface GetProductByIdResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Update Product Price",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UpdateProductPriceExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Product Id Not Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Product Not Found",
                                                    value = "{\"data\": null, \"message\" : \"Product not found\"}"
                                            )
                                    }
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "User Not Authorized to Access Endpoint",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Unauthorized User",
                                                    value = "{\"data\": null, \"message\" : \"You are not authorized to access\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface UpdateProductPriceResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Delete Product",
                    content = {
                            @Content(mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Delete Product",
                                                    value = "{\"message\" : \"success\"}"
                                            )
                                    }
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Product Not Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Product Not Found",
                                                    value = "{\"data\": null, \"message\" : \"Product not found\"}"
                                            )
                                    }
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "User Not Authorized to Access Endpoint",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Unauthorized User",
                                                    value = "{\"data\": null, \"message\" : \"You are not authorized to access\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface DeleteProductResponses{
    }

    // Annotations for Order in below
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success Create New Order",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CreateOrderExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Restaurant Not Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Can't Find Restaurant Id",
                                                    value = "{\"data\": null, \"message\" : \"Restaurant Not Found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface CreateOrderResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success Confirm Order",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ConfirmOrderExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Order Id Not Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Can't Find Order Id",
                                                    value = "{\"data\": null, \"message\" : \"Order Not Found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface ConfirmOrderResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Get All Order",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetAllOrderExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "User Not Authorized to Access Endpoint",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Unauthorized User",
                                                    value = "{\"data\": null, \"message\" : \"You are not authorized to access\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface GetAllOrderResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Get Order",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetOrderByIdExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Order Id Not Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Can't Find Order Id",
                                                    value = "{\"data\": null, \"message\" : \"Order Not Found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface GetOrderByIdResponses{
    }

    // Annotations for Order Item in below
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success Create New Order Item",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CreateOrderExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Can't Find Order Id",
                                                    value = "{\"data\": null, \"message\" : \"Active order not found\"}"
                                            ),
                                            @ExampleObject(
                                                    name = "Closed Order",
                                                    value = "{\"data\": null, \"message\" : \"Please create a new order, last order has been closed\"}"
                                            ),
                                            @ExampleObject(
                                                    name = "Product Not Found",
                                                    value = "{\"data\": null, \"message\" : \"Product Not Found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface CreateOrderItemResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Update Quantity Order Item",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CreateOrderExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Can't Find Order Id",
                                                    value = "{\"data\": null, \"message\" : \"Active order not found\"}"
                                            ),
                                            @ExampleObject(
                                                    name = "Closed Order",
                                                    value = "{\"data\": null, \"message\" : \"Please create a new order, last order has been closed\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface UpdateOrderItemQtyResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Get All Order Item",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetAllOrderItemExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "User Not Authorized to Access Endpoint",
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
    public @interface GetAllOrderItemResponses{
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Get Order Item",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetOrderItemByOrderIdExampleSwagger.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Order Item Not Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Order Item Not Found",
                                                    value = "{\"data\": null, \"message\" : \"Order Item Not Found\"}"
                                            )
                                    }
                            )
                    }
            )
    })
    public @interface GetOrderItemByOrderIdResponses{
    }
}
