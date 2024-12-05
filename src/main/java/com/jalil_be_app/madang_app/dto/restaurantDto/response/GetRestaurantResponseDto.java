package com.jalil_be_app.madang_app.dto.restaurantDto.response;

import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.enums.RestaurantCategory;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetRestaurantResponseDto {
    private UUID restaurantId;
    private String name;
    private String description;
    private String address;
    private RestaurantCategory category;
    private Image image;
}
