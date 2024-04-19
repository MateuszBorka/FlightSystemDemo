package com.example.flightsystemdemo.flightairport.entity;

import com.example.flightsystemdemo.airport.entity.Airport;
import com.example.flightsystemdemo.flight.entity.Flight;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightAirport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    private int sequenceNumber;

}
