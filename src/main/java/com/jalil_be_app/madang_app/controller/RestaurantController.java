package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.dto.restaurantDto.UpdateRestaurantAddressRequestDto;
import com.jalil_be_app.madang_app.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<Map<String, Object>> create(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateRestaurantRequestDto createRestaurantRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", restaurantService.create(token, createRestaurantRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("update-address")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<Map<String, Object>> update(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateRestaurantAddressRequestDto updateRestaurantAddressRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", restaurantService.update(token, updateRestaurantAddressRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public void delete(@RequestHeader("Authorization") String token){
        restaurantService.delete(token);
    }
}
