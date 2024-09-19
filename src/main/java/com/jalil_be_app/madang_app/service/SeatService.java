package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.seatDto.CheckSeatReq;
import com.jalil_be_app.madang_app.dto.seatDto.request.CreateSeatRequestDto;
import com.jalil_be_app.madang_app.dto.seatDto.response.CreateSeatResponseDto;
import com.jalil_be_app.madang_app.model.entity.Seat;

public interface SeatService {
    CreateSeatResponseDto create(String token, CreateSeatRequestDto createSeatRequestDto);
    Seat findByCategory(CheckSeatReq checkSeatReq);
}
