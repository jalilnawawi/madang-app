package com.jalil_be_app.madang_app.dto.restaurantDto;

import com.jalil_be_app.madang_app.model.enums.RestaurantCategory;
import lombok.Data;

@Data
public class CreateRestaurantRequestDto {
    private String name;
    private String description;
    private String address;
    private String category;
    private String imageLink;
}
