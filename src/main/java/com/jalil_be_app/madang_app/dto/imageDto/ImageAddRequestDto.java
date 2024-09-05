package com.jalil_be_app.madang_app.dto.imageDto;

import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import com.jalil_be_app.madang_app.model.enums.ImageSize;
import lombok.Data;

@Data
public class ImageAddRequestDto {
    private String imageLink;
    private ImageCategory imageCategory;
    private ImageSize imageSize;
}
