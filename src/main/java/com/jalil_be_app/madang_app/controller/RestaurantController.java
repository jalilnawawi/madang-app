package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.restaurantDto.CreateRestaurantRequestDto;
import com.jalil_be_app.madang_app.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody CreateRestaurantRequestDto createRestaurantRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", restaurantService.create(createRestaurantRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
