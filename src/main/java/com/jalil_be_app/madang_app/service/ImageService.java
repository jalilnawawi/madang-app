package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.imageDto.request.ImageAddRequestDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface ImageService {
    Image add(ImageAddRequestDto imageAddRequestDto);
//    String uploadImage(MultipartFile imageFile) throws IOException;
    Image uploadImage(MultipartFile file) throws IOException;
//    byte[] downloadImage(UUID imageId);
    Image unduhImage(UUID imageId);
}
