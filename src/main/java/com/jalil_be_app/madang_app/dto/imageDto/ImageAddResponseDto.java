package com.jalil_be_app.madang_app.dto.imageDto;

import com.jalil_be_app.madang_app.model.enums.ImageCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageAddResponseDto {
    private String imageLink;
    private ImageCategory imageCategory;
}
