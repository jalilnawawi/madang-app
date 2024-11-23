package com.jalil_be_app.madang_app.dto.schemes.restaurant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantExampleSwagger {
    @Schema(example = "Ayam Geprek Kumlot")
    private String name;
    @Schema(example = "Ayam Geprek murah + Free refill nasi")
    private String description;
    @Schema(example = "Ngoresan, Jebres, Surakarta")
    private String address;
    @Schema(example = "Warung Makan")
    private String category;
    @Schema(example = "geprekKumlot_image")
    private String imageLink;
    @Schema(name = "message", example = "success")
    private String message;
}
