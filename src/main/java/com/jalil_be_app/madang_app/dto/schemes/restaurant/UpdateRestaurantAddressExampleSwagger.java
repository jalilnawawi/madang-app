package com.jalil_be_app.madang_app.dto.schemes.restaurant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRestaurantAddressExampleSwagger {
    @Schema(example = "Jl. Pramuka no. 45A, Jebres, Surakarta")
    private String address;
}
