package com.example.flightsystemdemo.flight.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@SuperBuilder
@Getter
public class FlightRequestDto extends FlightBaseDto {

    @Size(min = 2, message = "At least two flight airports are required")
    private List<Long> flightAirports;
}
