package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.base.BaseResponse;
import com.jalil_be_app.madang_app.dto.imageDto.ImageAddRequestDto;
import com.jalil_be_app.madang_app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/image")
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping("add")
    public ResponseEntity<Map<String, Object>> add(ImageAddRequestDto imageAddRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", imageService.add(imageAddRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
