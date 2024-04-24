package com.example.flightsystemdemo.airport.dto;

import com.example.flightsystemdemo.validation.ValidationConsts;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportBaseDto {



    @Pattern(regexp = ValidationConsts.IATA_CODE_PATTERN, message = "IATA code must consist of exactly three uppercase alphabetic characters")
    @NotBlank
    private String code;

    @Range(min = -90, max = 90, message = "Latitude must be in the range from -90 to 90")
    private double latitude;

    @Range(min = -180, max = 180, message = "Longitude must be in the range from -180 to 180")
    private double longitude;

    @Size(min = ValidationConsts.MIN_SIZE_NAME, max = ValidationConsts.MAX_SIZE_NAME)
    @NotBlank
    private String name;
}
