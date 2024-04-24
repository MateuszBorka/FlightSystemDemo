package com.example.flightsystemdemo.airport.dto;

import com.example.flightsystemdemo.validation.ValidationConsts;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportBaseDto {



    @Size(min = ValidationConsts.SIZE_CODE, max = ValidationConsts.SIZE_CODE)
    @NotBlank
    private String code;

    @Min(value = -90, message = "Latitude must be greater than or equal to -90")
    @Max(value = 90, message = "Latitude must be less than or equal to 90")
    private double latitude;

    @Min(value = -180, message = "Longitude must be greater than or equal to -180")
    @Max(value = 180, message = "Longitude must be less than or equal to 180")
    private double longitude;


    @Size(min = ValidationConsts.MIN_SIZE_NAME, max = ValidationConsts.MAX_SIZE_NAME)
    @NotBlank
    private String name;
}
