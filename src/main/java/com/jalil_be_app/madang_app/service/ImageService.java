package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.imageDto.request.ImageAddRequestDto;
import com.jalil_be_app.madang_app.model.entity.Image;

public interface ImageService {
    Image add(ImageAddRequestDto imageAddRequestDto);
}
