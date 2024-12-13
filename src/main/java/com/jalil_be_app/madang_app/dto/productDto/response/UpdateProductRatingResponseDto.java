package com.jalil_be_app.madang_app.dto.productDto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRatingResponseDto {
    private UUID productId;
    private Float rating;
}
