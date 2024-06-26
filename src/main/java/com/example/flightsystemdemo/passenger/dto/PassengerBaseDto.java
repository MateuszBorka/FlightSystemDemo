package com.example.flightsystemdemo.passenger.dto;

import com.example.flightsystemdemo.validation.ValidationConsts;
import com.example.flightsystemdemo.validation.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class PassengerBaseDto {

    @Size(
            min = ValidationConsts.MIN_SIZE_NAME,
            max = ValidationConsts.MAX_SIZE_NAME,
            message = "Name length must be between {min} and {max} characters"
    )
    @Pattern(regexp = ValidationConsts.NAME_NO_SPACES_PATTERN, message = ValidationMessages.NAME)
    @NotBlank
    private String name;

    @Size(min = ValidationConsts.MIN_SIZE_SURNAME, max = ValidationConsts.MAX_SIZE_SURNAME)
    @Pattern(regexp = ValidationConsts.SURNAME_NO_SPACES_PATTERN, message = ValidationMessages.SURNAME)
    @NotBlank
    private String surname;


    @Size(
            min = ValidationConsts.MIN_PHONE_NUMBER_LENGTH,
            max = ValidationConsts.MAX_PHONE_NUMBER_LENGTH,
            message = "Phone number length must be between {min} and {max} characters"
    )
    @Pattern(
            regexp = ValidationConsts.PHONE_NUMBER_ONLY_NUMBERS_PATTERN,
            message = "Contact phone must contain only numbers"
    )
    @NotBlank
    private String contactPhone;
}
