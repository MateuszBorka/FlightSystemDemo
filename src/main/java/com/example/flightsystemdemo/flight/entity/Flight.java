package com.example.flightsystemdemo.flight.entity
        ;


import com.example.flightsystemdemo.airport.entity.Airport;
import com.example.flightsystemdemo.passenger.entity.Passenger;
import com.example.flightsystemdemo.validation.ValidationConsts;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // TODO: numer lotu

    @Max(ValidationConsts.MAX_FREE_PLACES)
    @Min(ValidationConsts.MIN_FREE_PLACES)
    private Integer freePlaces;

    @ManyToMany
    @JoinTable(
            name = "flight_airport",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "airport_id")
    )
    private List<Airport> flightAirports;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passenger> passengers ;

    LocalDateTime departureDateAndTime;









}
