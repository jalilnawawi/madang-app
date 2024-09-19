package com.jalil_be_app.madang_app.dto.imageDto.request;

import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import lombok.Data;

@Data
public class ImageAddRequestDto {
    private String imageLink;
    private ImageCategory imageCategory;
}
