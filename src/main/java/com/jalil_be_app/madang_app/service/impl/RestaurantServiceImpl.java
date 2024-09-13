package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantResponseDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.UpdateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.UpdateRestaurantResponseDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.entity.Restaurant;
import com.jalil_be_app.madang_app.model.entity.account.User;
import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.model.enums.ImageSize;
import com.jalil_be_app.madang_app.model.enums.RestaurantCategory;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.repository.RestaurantRepository;
import com.jalil_be_app.madang_app.repository.UserRepository;
import com.jalil_be_app.madang_app.service.RestaurantService;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    JwtService jwtService;

    @Transactional
    @Override
    public CreateRestaurantResponseDto create(String token, CreateRestaurantRequestDto createRestaurantRequestDto) {
        String jwtToken = token.substring("Bearer ".length());
        String userId = jwtService.getId(jwtToken);
        UUID userIdFromString = UUID.fromString(userId);

        User existingUser = userRepository.findById(userIdFromString).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")
        );

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

        restaurant.setUser(existingUser);
        restaurantRepository.save(restaurant);

        CreateRestaurantResponseDto responseDto = new CreateRestaurantResponseDto();
        responseDto.setName(createRestaurantRequestDto.getName());
        responseDto.setDescription(createRestaurantRequestDto.getDescription());
        responseDto.setAddress(createRestaurantRequestDto.getAddress());
        responseDto.setCategory(createRestaurantRequestDto.getCategory());
        responseDto.setImageLink(createRestaurantRequestDto.getImageLink());
        return responseDto;
    }

    @Override
    public UpdateRestaurantResponseDto update(String token, UpdateRestaurantRequestDto updateRestaurantRequestDto) {
        String jwtToken = token.substring("Bearer ".length());
        String userId = jwtService.getId(jwtToken);
        UUID userIdFromString = UUID.fromString(userId);

        Restaurant existingRestaurant = restaurantRepository.findByUserId(userIdFromString).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        existingRestaurant.setName(updateRestaurantRequestDto.getName());
        existingRestaurant.setDescription(updateRestaurantRequestDto.getDescription());
        existingRestaurant.setAddress(updateRestaurantRequestDto.getAddress());
        restaurantRepository.save(existingRestaurant);

        UpdateRestaurantResponseDto responseDto = new UpdateRestaurantResponseDto();
        responseDto.setName(updateRestaurantRequestDto.getName());
        responseDto.setDescription(updateRestaurantRequestDto.getDescription());
        responseDto.setAddress(updateRestaurantRequestDto.getAddress());
        return responseDto;
    }
}
