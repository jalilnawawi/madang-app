package com.jalil_be_app.madang_app.dto.restaurantDto.response;

import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.enums.RestaurantCategory;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllRestaurantResponseDto {
    private UUID restaurantId;
    @Schema(example = "Warteg Bahari")
    private String name;
    @Schema(example = "Warteg murah sahabat mahasiswa")
    private String description;
    @Schema(example = "Ngoresan")
    private String address;
    @Schema(example = "Indonesian food")
    private RestaurantCategory category;
    private Image image;
}
