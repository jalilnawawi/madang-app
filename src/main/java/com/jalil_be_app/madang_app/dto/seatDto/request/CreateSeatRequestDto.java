package com.jalil_be_app.madang_app.dto.seatDto.request;

import com.jalil_be_app.madang_app.model.enums.SeatCategory;
import lombok.Data;

@Data
public class CreateSeatRequestDto {
    private String name;
    private int countTables;
    private int countChairs;
    private int countFlowers;
    private Double price;
    private String category;
}
