package com.example.flightsystemdemo.passenger.dto;

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
public class PassengerResponseDto extends PassengerBaseDto{

    private long id;
}
