package com.example.flightsystemdemo.flight.controller;

import com.example.flightsystemdemo.flight.dto.FlightRequestDto;
import com.example.flightsystemdemo.flight.dto.FlightResponseDto;
import com.example.flightsystemdemo.flight.entity.Flight;
import com.example.flightsystemdemo.flight.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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

    @PostMapping("flights/{flightId}/passengers/{passengerId}")
    public ResponseEntity<?> addPassengerToFlight( @PathVariable long flightId, @PathVariable long passengerId){
        Optional<FlightResponseDto> updateFlightResult = flightService.addPassenger(flightId, passengerId);
        if (!updateFlightResult.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(updateFlightResult.get());
        }
    }

    @DeleteMapping("flights/{flightId}/passengers/{passengerId}")
    public ResponseEntity<?> removePassengerFromFlight( @PathVariable long flightId, @PathVariable long passengerId){
        Optional<FlightResponseDto> updateFlightResult = flightService.removePassenger(flightId, passengerId);
        if (!updateFlightResult.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(updateFlightResult.get());
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

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getFlights(
            @RequestParam(name = "airportId", required = false) Long airportId,
            @RequestParam(name = "minDateTime", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime minDateTime,
            @RequestParam(name = "maxDateTime", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime maxDateTime,
            @RequestParam(name = "minSeats", required = false) Integer minSeats,
            @RequestParam(name = "maxSeats", required = false) Integer maxSeats) {

        // Apply logic to filter flights based on the provided parameters
        List<Flight> filteredFlights = flightService.getFilteredFlights(airportId, minDateTime, maxDateTime, minSeats, maxSeats);

        return ResponseEntity.ok(filteredFlights);
    }
}
