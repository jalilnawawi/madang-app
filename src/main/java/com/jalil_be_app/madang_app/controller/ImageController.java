package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.imageDto.request.ImageAddRequestDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @PostMapping("upload")
    public ResponseEntity<Map<String, Object>> uploadImage(
            @RequestParam("image") MultipartFile file
    ) throws IOException {
//        String uploadImage = imageService.uploadImage(file);
        Image uploadImage = imageService.uploadImage(file);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", uploadImage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get-image/{imageId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable UUID imageId){
//        byte[] imageData = imageService.downloadImage(imageId);
        Image image = imageService.downloadImage(imageId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(image.getType()));

//        return ResponseEntity.status(HttpStatus.OK)
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename=\"" + image.getImageName() + "\""
//                        )
//                .body(image.getImageData());
        return new ResponseEntity<>(image.getImageData(), headers, HttpStatus.OK);
    }
}
