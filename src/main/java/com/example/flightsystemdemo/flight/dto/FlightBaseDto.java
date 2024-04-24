package com.example.flightsystemdemo.flight.dto;

import com.example.flightsystemdemo.validation.ValidationConsts;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightBaseDto {

    @Range(
            min = ValidationConsts.MIN_FREE_PLACES,
            max = ValidationConsts.MAX_FREE_PLACES
    )
    private int freePlaces;

    @Future(message = "Departure date and time must be in the future")
    LocalDateTime departureDateAndTime;

}
