package com.example.flightsystemdemo.passenger.controller;

import com.example.flightsystemdemo.passenger.service.PassengerService;
import com.example.flightsystemdemo.passenger.dto.PassengerResponseDto;
import com.example.flightsystemdemo.passenger.dto.PassengerRequestDto;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping("passengers/{id}")
    public ResponseEntity<PassengerResponseDto> getPassengerById(@PathVariable long id){
        return passengerService.getPassengerById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
