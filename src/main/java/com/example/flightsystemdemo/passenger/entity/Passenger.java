package com.example.flightsystemdemo.passenger.entity;


import com.example.flightsystemdemo.validation.ValidationConsts;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = ValidationConsts.MAX_SIZE_NAME)
    private String name;

    @NotNull
    @Size(max = ValidationConsts.MAX_SIZE_SURNAME)
    private String surname;

    @NotNull
    @Size(max = ValidationConsts.MAX_PHONE_NUMBER_LENGTH)
    private String contactPhone;


}
