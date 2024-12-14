package com.jalil_be_app.madang_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jalil_be_app.madang_app.dto.restaurantDto.request.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.request.UpdateRestaurantAddressRequestDto;
import com.jalil_be_app.madang_app.service.RestaurantService;
import com.jalil_be_app.madang_app.utils.ApiResponseAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping(value = "create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @ApiResponseAnnotations.CreateRestaurantResponses
    public ResponseEntity<Map<String, Object>> create(
            @RequestHeader("Authorization") String token,
            @RequestPart("data") String createRestaurantDto,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        CreateRestaurantRequestDto createRestaurantRequestDto = convertToString(createRestaurantDto);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", restaurantService.create(token, createRestaurantRequestDto, file));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private CreateRestaurantRequestDto convertToString(String createRestaurantDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(createRestaurantDto, CreateRestaurantRequestDto.class);
    }

    @PutMapping("update-address")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @ApiResponseAnnotations.UpdateRestaurantAddressResponses
    public ResponseEntity<Map<String, Object>> update(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateRestaurantAddressRequestDto updateRestaurantAddressRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", restaurantService.update(token, updateRestaurantAddressRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @ApiResponseAnnotations.DeleteRestaurantResponses
    public void delete(@PathVariable("id") UUID restaurantId){
        restaurantService.delete(restaurantId);
    }

    @GetMapping("get-all-restaurant")
    @ApiResponseAnnotations.GetAllRestaurantResponses
    public ResponseEntity<Map<String, Object>> getAll(){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", restaurantService.getAllRestaurant());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-restaurant-by-id/{id}")
//    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @ApiResponseAnnotations.GetRestaurantByIdResponses
    public ResponseEntity<Map<String, Object>> getById(@PathVariable("id") UUID id){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", restaurantService.getRestaurantById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-restaurant-by-userId/{userId}")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    @ApiResponseAnnotations.GetAllRestaurantByUserIdResponses
    public ResponseEntity<Map<String, Object>> getByUserId(@PathVariable("userId") UUID userId){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", restaurantService.getRestaurantByUserId(userId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, Object>> searchRestaurant(@RequestParam String searchText){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success get product related to " + searchText);
        response.put("data", restaurantService.getRestaurantBySearch(searchText));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
