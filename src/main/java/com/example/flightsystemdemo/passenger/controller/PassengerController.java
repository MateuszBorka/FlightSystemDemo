package com.example.flightsystemdemo.passenger.controller;

import com.example.flightsystemdemo.passenger.dto.PassengerRequestDto;
import com.example.flightsystemdemo.passenger.dto.PassengerResponseDto;
import com.example.flightsystemdemo.passenger.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping("passengers/{id}")
    public ResponseEntity<PassengerResponseDto> getPassengerById(@PathVariable long id) {
        return passengerService.getPassengerById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("passengers")
    public ResponseEntity<?> createPassenger(@Valid @RequestBody PassengerRequestDto passengerRequestDto) {
        final Optional<PassengerResponseDto> createPassengerResult = passengerService.createPassenger(passengerRequestDto);
        // In more advanced system I would rather use Either<CustomError, PassengerResponseDto> to have in this CustomError class more accurate error message to be thrown to the FrontEnd.

        if (!createPassengerResult.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(createPassengerResult.get());
        }
    }

    @PutMapping("passengers/{id}")
    public ResponseEntity<?> updatePassengerById(
            @PathVariable long id,
            @Valid @RequestBody PassengerRequestDto passengerRequestDto
    ) {
        final Optional<PassengerResponseDto> updatePassengerResult = passengerService.updatePassenger(id, passengerRequestDto);

        if (!updatePassengerResult.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(updatePassengerResult.get());
        }
    }

    @DeleteMapping("passengers/{id}")
    public ResponseEntity<?> deletePassengerById(@PathVariable long id) {
        boolean deleteResult = passengerService.deletePassenger(id);

        if (!deleteResult) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
