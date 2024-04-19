package com.example.flightsystemdemo.passenger.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.example.flightsystemdemo.validation.ValidationConsts;

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
