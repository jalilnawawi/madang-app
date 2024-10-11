package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.restaurantDto.request.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.response.CreateRestaurantResponseDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.request.UpdateRestaurantAddressRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.response.UpdateRestaurantAddressResponseDto;
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

import java.util.Optional;
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

    @Override
    @Transactional
    public CreateRestaurantResponseDto create(String token, CreateRestaurantRequestDto createRestaurantRequestDto) {
        UUID userIdFromToken = jwtService.getUserIdfromToken(token);

        User existingUser = userRepository.findById(userIdFromToken).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")
        );

        Optional<Restaurant> existingRestaurant = restaurantRepository.findByUserId(userIdFromToken);
        if (existingRestaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User only can have 1 Restaurant");
        } else {
            Restaurant restaurant = new Restaurant();
            if(createRestaurantRequestDto.getName() == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant name can't empty");
            } else {
                restaurant.setName(createRestaurantRequestDto.getName());
            }
            restaurant.setDescription(createRestaurantRequestDto.getDescription());

            if (createRestaurantRequestDto.getAddress() == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant address can't empty");
            } else {
                restaurant.setAddress(createRestaurantRequestDto.getAddress());
            }

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

    }

    @Override
    @Transactional
    public UpdateRestaurantAddressResponseDto update(String token, UpdateRestaurantAddressRequestDto updateRestaurantAddressRequestDto) {
        UUID userIdFromToken = jwtService.getUserIdfromToken(token);

        Restaurant existingRestaurant = restaurantRepository.findByUserId(userIdFromToken).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        existingRestaurant.setAddress(updateRestaurantAddressRequestDto.getAddress());
        restaurantRepository.save(existingRestaurant);

        UpdateRestaurantAddressResponseDto responseDto = new UpdateRestaurantAddressResponseDto();
        responseDto.setAddress(updateRestaurantAddressRequestDto.getAddress());
        return responseDto;
    }

    @Override
    @Transactional
    public void delete(String token, UUID restaurantId) {
        UUID userIdFromToken = jwtService.getUserIdfromToken(token);
        Restaurant existingRestaurant = restaurantRepository.findByUserId(userIdFromToken).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        restaurantRepository.deleteById(restaurantId);
    }
}
