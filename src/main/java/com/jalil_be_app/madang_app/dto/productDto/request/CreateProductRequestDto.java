package com.jalil_be_app.madang_app.dto.productDto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class CreateProductRequestDto {
    private UUID restaurantId;
    @Schema(example = "Espresso Coffee")
    private String name;
    @Schema(example = "15000")
    private Double price;
    @Schema(example = "DRINK")
    private String category;
//    @Schema(example = "espresso_image")
//    private String imageLink;
//    MultipartFile file;
}
