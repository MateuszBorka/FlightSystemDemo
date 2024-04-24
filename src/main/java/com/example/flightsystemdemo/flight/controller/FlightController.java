package com.example.flightsystemdemo.flight.controller;

import com.example.flightsystemdemo.flight.dto.FlightRequestDto;
import com.example.flightsystemdemo.flight.dto.FlightResponseDto;
import com.example.flightsystemdemo.flight.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class FlightController {

    private final FlightService flightService;

    @GetMapping("flights/{id}")
    public ResponseEntity<FlightResponseDto> getPassengerById(@PathVariable long id) {
        return flightService.getFlightById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("flights")
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightRequestDto flightRequestDto, BindingResult result) {

        ResponseEntity<?> errors = getValidationResult(result);
        if (errors != null) return errors;

        final Optional<FlightResponseDto> createFlightResult = flightService.createFlight(flightRequestDto);

        if (!createFlightResult.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(createFlightResult.get());
        }
    }

    @PutMapping("flights/{id}")
    public ResponseEntity<?> updateFlightById(
            @PathVariable long id,
            @Valid @RequestBody FlightRequestDto flightRequestDto,
            BindingResult result
    ) {

        ResponseEntity<?> errors = getValidationResult(result);
        if (errors != null) return errors;

        final Optional<FlightResponseDto> updateFlightResult = flightService.updateFlight(id, flightRequestDto);

        if (!updateFlightResult.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(updateFlightResult.get());
        }
    }

    @DeleteMapping("flights/{id}")
    public ResponseEntity<?> deleteFlightById(@PathVariable long id) {
        boolean deleteResult = flightService.deleteFlight(id);

        if (!deleteResult) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    private ResponseEntity<?> getValidationResult(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return null;
    }
}
