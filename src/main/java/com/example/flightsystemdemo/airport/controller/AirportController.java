package com.example.flightsystemdemo.airport.controller;

import com.example.flightsystemdemo.airport.dto.AirportRequestDto;
import com.example.flightsystemdemo.airport.dto.AirportResponseDto;
import com.example.flightsystemdemo.airport.service.AirportService;
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
public class AirportController {

    private final AirportService airportService;

    @GetMapping("airports/{id}")
    public ResponseEntity<AirportResponseDto> getAirportById(@PathVariable long id) {
        return airportService.getAirportById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("airports")
    public ResponseEntity<?> createAirport(@Valid @RequestBody AirportRequestDto airportRequestDto, BindingResult result) {

        ResponseEntity<?> errors = getValidationResult(result);
        if (errors != null) return errors;

        final Optional<AirportResponseDto> createAirportResult = airportService.createAirport(airportRequestDto);

        if (!createAirportResult.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(createAirportResult.get());
        }
    }

    @PutMapping("airports/{id}")
    public ResponseEntity<?> updateAirportById(
            @PathVariable long id,
            @Valid @RequestBody AirportRequestDto airportRequestDto,
            BindingResult result
    ) {

        ResponseEntity<?> errors = getValidationResult(result);
        if (errors != null) return errors;

        final Optional<AirportResponseDto> updateAirportResult = airportService.updateAirport(id, airportRequestDto);

        if (!updateAirportResult.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(updateAirportResult.get());
        }
    }

    @DeleteMapping("airports/{id}")
    public ResponseEntity<?> deleteAirportById(@PathVariable long id) {
        boolean deleteResult = airportService.deleteAirport(id);

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
