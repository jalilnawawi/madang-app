package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.productDto.request.CreateProductRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.response.CreateProductResponseDto;
import com.jalil_be_app.madang_app.dto.productDto.request.UpdateProductPriceRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.response.UpdateProductPriceResponseDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.entity.Product;
import com.jalil_be_app.madang_app.model.entity.Restaurant;
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

import java.util.List;
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
    @Transactional
    public CreateProductResponseDto create(String token, CreateProductRequestDto createProductRequestDto) {
        UUID userIdFromToken = jwtService.getUserIdfromToken(token);

        Restaurant existingRestaurant = restaurantRepository.findByUserId(userIdFromToken).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        Product product = new Product();
        if (createProductRequestDto.getName() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name can't empty");
        } else {
            product.setName(createProductRequestDto.getName());
        }

        if (createProductRequestDto.getPrice() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product price can't empty");
        } else {
            product.setPrice(createProductRequestDto.getPrice());
        }

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
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public UpdateProductPriceResponseDto update(String token, UUID productId, UpdateProductPriceRequestDto updateProductPriceRequestDto) {
        UUID userIdFromToken = jwtService.getUserIdfromToken(token);

        Restaurant existingRestaurant = restaurantRepository.findByUserId(userIdFromToken).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        if (userIdFromToken != existingRestaurant.getUser().getId()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to change");
        }

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
        UUID userIdFromToken = jwtService.getUserIdfromToken(token);

        Restaurant existingRestaurant = restaurantRepository.findByUserId(userIdFromToken).orElseThrow(
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
