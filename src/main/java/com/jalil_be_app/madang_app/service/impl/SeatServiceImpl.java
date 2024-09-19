package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.seatDto.request.CreateSeatRequestDto;
import com.jalil_be_app.madang_app.dto.seatDto.response.CreateSeatResponseDto;
import com.jalil_be_app.madang_app.model.entity.Seat;
import com.jalil_be_app.madang_app.model.entity.account.User;
import com.jalil_be_app.madang_app.model.enums.SeatCategory;
import com.jalil_be_app.madang_app.repository.SeatRepository;
import com.jalil_be_app.madang_app.repository.UserRepository;
import com.jalil_be_app.madang_app.service.SeatService;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Override
    public CreateSeatResponseDto create(String token, CreateSeatRequestDto createSeatRequestDto) {
        String jwtToken = token.substring("Bearer ".length());
        String userId = jwtService.getId(jwtToken);
        UUID userIdFromString = UUID.fromString(userId);

        User existingUser = userRepository.findById(userIdFromString).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")
        );

        Seat seat = new Seat();
        seat.setName(createSeatRequestDto.getName());
        seat.setCountTables(createSeatRequestDto.getCountTables());
        seat.setCountChairs(createSeatRequestDto.getCountChairs());
        seat.setCountFlowers(createSeatRequestDto.getCountFlowers());

        if (createSeatRequestDto.getCategory().equalsIgnoreCase("family")){
            seat.setCategory(SeatCategory.FAMILY);
        } else if (createSeatRequestDto.getCategory().equalsIgnoreCase("work")) {
            seat.setCategory(SeatCategory.WORK);
        } else if (createSeatRequestDto.getCategory().equalsIgnoreCase("custom")) {
            seat.setCategory(SeatCategory.CUSTOM);
        }
        seat.setPrice(createSeatRequestDto.getPrice());
        seatRepository.save(seat);

        CreateSeatResponseDto createSeatResponseDto = new CreateSeatResponseDto();
        createSeatResponseDto.setName(createSeatRequestDto.getName());
        createSeatResponseDto.setCountTables(createSeatRequestDto.getCountTables());
        createSeatResponseDto.setCountChairs(createSeatRequestDto.getCountChairs());
        createSeatResponseDto.setCountFlowers(createSeatRequestDto.getCountFlowers());
        createSeatResponseDto.setCategory(createSeatRequestDto.getCategory());
        createSeatResponseDto.setPrice(createSeatRequestDto.getPrice());
        return createSeatResponseDto;
    }

    @Override
    public Seat findByCategory(String category) {
        if (category.equalsIgnoreCase("family")){
            Seat familySeat = seatRepository.findByCategory(SeatCategory.FAMILY);
            return familySeat;
        } else if (category.equalsIgnoreCase("work")) {
            Seat workSeat = seatRepository.findByCategory(SeatCategory.WORK);
            return workSeat;
        } else {
            Seat customSeat = seatRepository.findByCategory(SeatCategory.CUSTOM);
            return customSeat;
        }
    }
}
