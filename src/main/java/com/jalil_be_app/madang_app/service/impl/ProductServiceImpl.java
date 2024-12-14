package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.productDto.request.CreateProductRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.request.UpdateProductRatingRequestDto;
import com.jalil_be_app.madang_app.dto.productDto.response.*;
import com.jalil_be_app.madang_app.dto.productDto.request.UpdateProductPriceRequestDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.entity.Product;
import com.jalil_be_app.madang_app.model.entity.Restaurant;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.repository.ProductRepository;
import com.jalil_be_app.madang_app.repository.RestaurantRepository;
import com.jalil_be_app.madang_app.service.ImageService;
import com.jalil_be_app.madang_app.service.ProductService;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    ImageService imageService;

    @Override
    @Transactional
    public CreateProductResponseDto create(String token, CreateProductRequestDto createProductRequestDto, MultipartFile file) throws IOException {
        Restaurant existingRestaurant = restaurantRepository.findById(createProductRequestDto.getRestaurantId()).orElseThrow(
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

        Image image = imageService.uploadImage(file);
        product.setImage(image);

        product.setRestaurant(existingRestaurant);
        productRepository.save(product);

        CreateProductResponseDto responseDto = new CreateProductResponseDto();
        responseDto.setProductId(product.getId());
        responseDto.setProductName(createProductRequestDto.getName());
        responseDto.setPrice(createProductRequestDto.getPrice());
        responseDto.setCategory(createProductRequestDto.getCategory());
        responseDto.setImageId(image.getId());
        responseDto.setRestaurantId(existingRestaurant.getId());
        responseDto.setRestaurantName(existingRestaurant.getName());
        responseDto.setUserId(existingRestaurant.getUser().getId());

        return responseDto;
    }

    @Override
    public List<GetAllProductResponseDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList
                .stream().map(
                        product -> new GetAllProductResponseDto(
                                product.getId(),
                                product.getName(),
                                product.getPrice(),
                                product.getCategory(),
                                product.getImage().getImageName(),
                                product.getRating(),
                                product.getRestaurant().getId(),
                                product.getRestaurant().getName(),
                                product.getRestaurant().getUser().getId()
                        )
                ).collect(Collectors.toList());
    }

    @Override
    public GetProductResponseDto getProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found")
        );

        GetProductResponseDto responseDto = new GetProductResponseDto();
        responseDto.setProductId(product.getId());
        responseDto.setProductName(product.getName());
        responseDto.setPrice(product.getPrice());
        responseDto.setCategoryName(product.getCategory());
        responseDto.setImageLink(product.getImage().getImageName());
        responseDto.setRating(product.getRating());
        responseDto.setRestaurantId(product.getRestaurant().getId());
        responseDto.setRestaurantName(product.getRestaurant().getName());
        responseDto.setUserId(product.getRestaurant().getUser().getId());
        return responseDto;
    }

    @Override
    public List<GetAllProductByRestoIdResponseDto> getProductByRestoId(UUID restaurantId) {
        List<Product> getAllProduct = productRepository.getProductByRestaurantId(restaurantId);
        return getAllProduct.stream().map(
                product -> new GetAllProductByRestoIdResponseDto(
                        product.getRestaurant().getId(),
                        product.getRestaurant().getName(),
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getCategory(),
                        product.getImage().getImageName(),
                        product.getRating(),
                        product.getRestaurant().getUser().getId()
                )
        ).collect(Collectors.toList());
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
        Restaurant existingRestaurant = restaurantRepository.findByUserId(productId).orElseThrow(
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

    @Override
    public List<GetAllProductResponseDto> getProductBySearch(String searchText) {
        List<Product> productList = productRepository.searchProduct(searchText);
        return productList.stream().map(
                product -> new GetAllProductResponseDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getCategory(),
                        product.getImage().getImageName(),
                        product.getRating(),
                        product.getRestaurant().getId(),
                        product.getRestaurant().getName(),
                        product.getRestaurant().getUser().getId()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public UpdateProductRatingResponseDto addRating(UUID productId, UpdateProductRatingRequestDto updateProductRatingRequestDto) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found")
        );

        existingProduct.setRating(updateProductRatingRequestDto.getAddRating());
        productRepository.save(existingProduct);

        UpdateProductRatingResponseDto responseDto = new UpdateProductRatingResponseDto();
        responseDto.setProductId(existingProduct.getId());
        responseDto.setRating(existingProduct.getRating());
        return responseDto;
    }
}
