package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantResponseDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.entity.Restaurant;
import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.model.enums.ImageSize;
import com.jalil_be_app.madang_app.model.enums.RestaurantCategory;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.repository.RestaurantRepository;
import com.jalil_be_app.madang_app.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ImageRepository imageRepository;

    @Transactional
    @Override
    public CreateRestaurantResponseDto create(CreateRestaurantRequestDto createRestaurantRequestDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(createRestaurantRequestDto.getName());
        restaurant.setDescription(createRestaurantRequestDto.getDescription());
        restaurant.setAddress(createRestaurantRequestDto.getAddress());

        if (createRestaurantRequestDto.getCategory().equalsIgnoreCase("coffee shop") ||
            createRestaurantRequestDto.getCategory().equalsIgnoreCase("coffeeshop")
        ){
            restaurant.setCategory(RestaurantCategory.COFFEE_SHOP);
        } else if (createRestaurantRequestDto.getCategory().equalsIgnoreCase("western")) {
            restaurant.setCategory(RestaurantCategory.WESTERN);
        }

        Image image = new Image();
        image.setImageLink(createRestaurantRequestDto.getImageLink());
        image.setSize(ImageSize.L);
        image.setCategory(ImageCategory.RESTAURANT);
        imageRepository.save(image);

        restaurant.setImage(image);
        restaurantRepository.save(restaurant);

        CreateRestaurantResponseDto responseDto = new CreateRestaurantResponseDto();
        responseDto.setName(createRestaurantRequestDto.getName());
        responseDto.setDescription(createRestaurantRequestDto.getDescription());
        responseDto.setAddress(createRestaurantRequestDto.getAddress());
        responseDto.setCategory(createRestaurantRequestDto.getCategory());
        responseDto.setImageLink(createRestaurantRequestDto.getImageLink());
        return responseDto;
    }
}
