package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.restaurantDto.request.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.response.*;
import com.jalil_be_app.madang_app.dto.restaurantDto.request.UpdateRestaurantAddressRequestDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.entity.Restaurant;
import com.jalil_be_app.madang_app.model.entity.account.User;
import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.model.enums.ImageSize;
import com.jalil_be_app.madang_app.model.enums.RestaurantCategory;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.repository.RestaurantRepository;
import com.jalil_be_app.madang_app.repository.UserRepository;
import com.jalil_be_app.madang_app.service.ImageService;
import com.jalil_be_app.madang_app.service.RestaurantService;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    JwtService jwtService;

    @Override
    @Transactional
    public CreateRestaurantResponseDto create(String token, CreateRestaurantRequestDto createRestaurantRequestDto, MultipartFile file) throws IOException {
        UUID userIdFromToken = jwtService.getUserIdfromToken(token);

        User existingUser = userRepository.findById(userIdFromToken).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")
        );

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

            Image image = imageService.uploadImage(file);
            image.setSize(ImageSize.L);
            image.setCategory(ImageCategory.RESTAURANT);
            imageRepository.save(image);

            restaurant.setImage(image);

            restaurant.setUser(existingUser);
            restaurantRepository.save(restaurant);

            CreateRestaurantResponseDto responseDto = new CreateRestaurantResponseDto();
            responseDto.setRestaurantId(restaurant.getId());
            responseDto.setUserId(existingUser.getId());
            responseDto.setRestaurantName(createRestaurantRequestDto.getName());
            responseDto.setDescription(createRestaurantRequestDto.getDescription());
            responseDto.setAddress(createRestaurantRequestDto.getAddress());
            responseDto.setCategory(createRestaurantRequestDto.getCategory());
            responseDto.setImageId(image.getId());
            responseDto.setImageLink(image.getImageLink());
            return responseDto;


    }

    @Override
    @Transactional
    public UpdateRestaurantAddressResponseDto update(UUID restaurantId, UpdateRestaurantAddressRequestDto updateRestaurantAddressRequestDto) {
//        UUID userIdFromToken = jwtService.getUserIdfromToken(token);

        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        existingRestaurant.setAddress(updateRestaurantAddressRequestDto.getAddress());
        restaurantRepository.save(existingRestaurant);

        UpdateRestaurantAddressResponseDto responseDto = new UpdateRestaurantAddressResponseDto();
        responseDto.setRestaurantId(existingRestaurant.getId());
        responseDto.setUserId(existingRestaurant.getUser().getId());
        responseDto.setAddress(updateRestaurantAddressRequestDto.getAddress());
        return responseDto;
    }

    @Override
    @Transactional
    public void delete(UUID restaurantId) {
//        UUID userIdFromToken = jwtService.getUserIdfromToken(token);
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        restaurantRepository.deleteById(existingRestaurant.getId());
    }

    @Override
    public List<GetAllRestaurantResponseDto> getAllRestaurant() {
        List<Restaurant> getAllRestaurant = restaurantRepository.findAll();
        return getAllRestaurant.stream().map(
                restaurant -> new GetAllRestaurantResponseDto(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getDescription(),
                        restaurant.getAddress(),
                        restaurant.getCategory(),
                        restaurant.getImage().getId(),
                        restaurant.getImage().getImageLink(),
                        restaurant.getUser().getId()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public GetRestaurantResponseDto getRestaurantById(UUID restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        GetRestaurantResponseDto responseDto = new GetRestaurantResponseDto();
        responseDto.setRestaurantId(restaurant.getId());
        responseDto.setName(restaurant.getName());
        responseDto.setAddress(restaurant.getAddress());
        responseDto.setDescription(restaurant.getDescription());
        responseDto.setCategory(responseDto.getCategory());
        responseDto.setImageId(restaurant.getImage().getId());
        responseDto.setImageLink(restaurant.getImage().getImageLink());
        responseDto.setUserId(restaurant.getUser().getId());
        return responseDto;
    }

    @Override
    public List<GetAllRestaurantResponseDto> getRestaurantByUserId(UUID userId) {
        List<Restaurant> getAllRestaurantFromUser = restaurantRepository.getRestaurantByUserId(userId);
        return getAllRestaurantFromUser.stream().map(
                restaurant -> new GetAllRestaurantResponseDto(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getDescription(),
                        restaurant.getAddress(),
                        restaurant.getCategory(),
                        restaurant.getImage().getId(),
                        restaurant.getImage().getImageLink(),
                        restaurant.getUser().getId()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<GetRestaurantbySearchResponseDto> getRestaurantBySearch(String searchText) {
        List<Restaurant> getRestaurantBySearch = restaurantRepository.searchRestaurant(searchText);
        return getRestaurantBySearch.stream().map(
                restaurant -> new GetRestaurantbySearchResponseDto(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getDescription(),
                        restaurant.getAddress(),
                        restaurant.getCategory(),
                        restaurant.getImage().getId(),
                        restaurant.getImage().getImageLink(),
                        restaurant.getUser().getId(),
                        searchText
                )
        ).collect(Collectors.toList());
    }
}
