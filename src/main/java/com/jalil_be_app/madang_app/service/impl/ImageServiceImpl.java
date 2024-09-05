package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.imageDto.ImageAddRequestDto;
import com.jalil_be_app.madang_app.model.entity.Image;
import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.model.enums.ImageSize;
import com.jalil_be_app.madang_app.repository.ImageRepository;
import com.jalil_be_app.madang_app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image add(ImageAddRequestDto imageAddRequestDto) {
        Image image = new Image();
        image.setImageLink(imageAddRequestDto.getImageLink());
//        image.setCategory(ImageCategory.USER_IMAGE);
        image.setSize(ImageSize.S);

        return imageRepository.save(image);
    }
}
