package com.example.flightsystemdemo.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportResponseDto extends AirportBaseDto {

    private long id;
}
