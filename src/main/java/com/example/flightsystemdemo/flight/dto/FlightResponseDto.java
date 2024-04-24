package com.example.flightsystemdemo.flight.dto;

import com.example.flightsystemdemo.airport.entity.Airport;
import com.example.flightsystemdemo.passenger.entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponseDto extends FlightBaseDto {

    private long id;

    private List<Passenger> passengers ;

    private List<Airport> flightAirports;
}
