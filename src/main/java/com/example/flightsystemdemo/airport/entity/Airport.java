package com.example.flightsystemdemo.airport.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import com.example.flightsystemdemo.validation.ValidationConsts;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = ValidationConsts.SIZE_CODE)
    private String code;
    @Min(value = -90)
    @Max(value = 90)
    private double latitude;
    @Min(value = -180)
    @Max(value = 180)
    private double longitude;

    @NotNull
    @Size(max = ValidationConsts.MAX_SIZE_NAME)
    private String name;




}
