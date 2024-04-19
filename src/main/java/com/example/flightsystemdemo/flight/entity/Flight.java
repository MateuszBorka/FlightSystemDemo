package com.example.flightsystemdemo.flight.entity
        ;


import com.example.flightsystemdemo.flightairport.entity.FlightAirport;
import com.example.flightsystemdemo.passenger.entity.Passenger;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.example.flightsystemdemo.validation.ValidationConsts;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // TODO: numer lotu

    @Size(max = ValidationConsts.MAX_SIZE_NAME)
    private int freePlaces;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequenceNumber")
    private List<FlightAirport> flightAirports; // TODO: trasa


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passenger> passengers ;

    LocalDateTime departureDateAndTime;









}
