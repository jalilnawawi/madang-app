package com.jalil_be_app.madang_app.dto.schemes.restaurant;

import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.enums.RestaurantCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetRestaurantByIdExampleSwagger {
    @Schema(example = "East Side Coffee")
    private String name;
    @Schema(example = "Coffeeshop harga mahasiswa")
    private String description;
    @Schema(example = "Ngoresan, Kecamatan Jebres, Kota Surakarta")
    private String address;
    @Schema(example = "COFFEE_SHOP")
    private RestaurantCategory category;
    private Image image;
}
