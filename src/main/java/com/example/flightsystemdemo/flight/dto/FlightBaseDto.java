package com.example.flightsystemdemo.flight.dto;

import com.example.flightsystemdemo.validation.ValidationConsts;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightBaseDto {

    @Max(ValidationConsts.MAX_FREE_PLACES)
    @Min(ValidationConsts.MIN_FREE_PLACES)
    private int freePlaces;

    @Future(message = "Departure date and time must be in the future")
    LocalDateTime departureDateAndTime;

}
