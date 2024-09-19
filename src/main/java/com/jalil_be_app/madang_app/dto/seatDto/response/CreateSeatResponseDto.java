package com.jalil_be_app.madang_app.dto.seatDto.response;

import com.jalil_be_app.madang_app.model.enums.SeatCategory;
import lombok.Data;

@Data
public class CreateSeatResponseDto {
    private String name;
    private int countTables;
    private int countChairs;
    private int countFlowers;
    private Double price;
    private String category;
}
