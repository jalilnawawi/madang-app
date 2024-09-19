package com.jalil_be_app.madang_app.controller;

import com.jalil_be_app.madang_app.dto.seatDto.CheckSeatReq;
import com.jalil_be_app.madang_app.dto.seatDto.request.CreateSeatRequestDto;
import com.jalil_be_app.madang_app.model.entity.Seat;
import com.jalil_be_app.madang_app.repository.SeatRepository;
import com.jalil_be_app.madang_app.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/seat")
public class SeatController {
    @Autowired
    SeatService seatService;
    @Autowired
    SeatRepository seatRepository;

    @PostMapping("create")
    @PreAuthorize("hasRole('ROLE_MERCHANT')")
    public ResponseEntity<Map<String, Object>> create(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateSeatRequestDto createSeatRequestDto
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", seatService.create(token, createSeatRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("check-seat")
    public ResponseEntity<Map<String, Object>> check(@RequestBody CheckSeatReq checkSeatReq){
//        Optional<Seat> seat = seatRepository.findById(checkSeatReq.getSeatId());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "success");
        response.put("data", seatService.findByCategory(checkSeatReq));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
