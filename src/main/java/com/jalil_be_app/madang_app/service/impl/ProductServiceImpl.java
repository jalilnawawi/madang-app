package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.productDto.CreateProductRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.CreateProductResponseDto;
import com.jalil_be_app.madang_app.dto.productDto.UpdateProductPriceRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.UpdateProductPriceResponseDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.entity.Product;
import com.jalil_be_app.madang_app.model.entity.Restaurant;
import com.jalil_be_app.madang_app.model.entity.account.User;
import com.jalil_be_app.madang_app.model.entity.account.UserRole;
import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.model.enums.ImageSize;
import com.jalil_be_app.madang_app.model.enums.ProductCategory;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.repository.ProductRepository;
import com.jalil_be_app.madang_app.repository.RestaurantRepository;
import com.jalil_be_app.madang_app.repository.UserRepository;
import com.jalil_be_app.madang_app.service.ProductService;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;
    @Override
    public CreateProductResponseDto create(String token, CreateProductRequestDto createProductRequestDto) {
        String jwtToken = token.substring("Bearer ".length());
        String userId = jwtService.getId(jwtToken);
        UUID userIdFromString = UUID.fromString(userId);

        Restaurant existingRestaurant = restaurantRepository.findByUserId(userIdFromString).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        Product product = new Product();
        product.setName(createProductRequestDto.getName());
        product.setPrice(createProductRequestDto.getPrice());

        Image image = new Image();
        if (createProductRequestDto.getCategory().equalsIgnoreCase("food")){
            product.setCategory(ProductCategory.FOOD);
            image.setCategory(ImageCategory.FOOD);
        } else if (createProductRequestDto.getCategory().equalsIgnoreCase("drink")) {
            product.setCategory(ProductCategory.DRINK);
            image.setCategory(ImageCategory.DRINK);
        }

        image.setSize(ImageSize.S);
        image.setImageLink(createProductRequestDto.getImageLink());

        product.setRestaurant(existingRestaurant);
        imageRepository.save(image);
        productRepository.save(product);

        CreateProductResponseDto responseDto = new CreateProductResponseDto();
        responseDto.setName(createProductRequestDto.getName());
        responseDto.setPrice(createProductRequestDto.getPrice());
        responseDto.setCategory(createProductRequestDto.getCategory());
        responseDto.setImageLink(createProductRequestDto.getImageLink());
        responseDto.setRestaurantName(existingRestaurant.getName());

        return responseDto;
    }

    @Override
    @Transactional
    public UpdateProductPriceResponseDto update(String token, UUID productId, UpdateProductPriceRequestDto updateProductPriceRequestDto) {
        String jwtToken = token.substring("Bearer ".length());
        String userId = jwtService.getId(jwtToken);
        UUID userIdFromString = UUID.fromString(userId);

        Restaurant existingRestaurant = restaurantRepository.findByUserId(userIdFromString).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        Product existingProduct = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found")
        );

        existingProduct.setPrice(updateProductPriceRequestDto.getPrice());
        productRepository.save(existingProduct);

        UpdateProductPriceResponseDto responseDto = new UpdateProductPriceResponseDto();
        responseDto.setName(existingProduct.getName());
        responseDto.setPrice(existingProduct.getPrice());
        responseDto.setCategory(existingProduct.getCategory().toString());
        responseDto.setRestaurantName(existingRestaurant.getName());
        return responseDto;
    }

    @Override
    public void delete(String token, UUID productId) {
        String jwtToken = token.substring("Bearer ".length());
        String userId = jwtService.getId(jwtToken);
        UUID userIdFromString = UUID.fromString(userId);

        Restaurant existingRestaurant = restaurantRepository.findByUserId(userIdFromString).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        Product existingProduct = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found")
        );

        if (!existingProduct.getRestaurant().getId().equals(existingRestaurant.getId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorize to action");
        }

        productRepository.deleteById(productId);
    }
}
