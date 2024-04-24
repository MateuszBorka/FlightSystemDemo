package com.example.flightsystemdemo.setup;

import com.example.flightsystemdemo.airport.entity.Airport;
import com.example.flightsystemdemo.airport.repository.AirportRepository;
import com.example.flightsystemdemo.passenger.entity.Passenger;
import com.example.flightsystemdemo.passenger.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final PassengerRepository passengerRepository;
    private final AirportRepository airportRepository;

    @Autowired
    public DatabaseInitializer(
            PassengerRepository passengerRepository,
            AirportRepository airportRepository) {
        this.passengerRepository = passengerRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public void run(String... args) {
        populateAirports();
        populatePassengers();
    }


    void populateAirports() {
        List<Airport> testAirports = Arrays.asList(
                new Airport(null, "JFK", 40.6413, -73.7781, "John F. Kennedy International Airport"),
                new Airport(null, "LAX", 33.9416, -118.4085, "Los Angeles International Airport"),
                new Airport(null, "ORD", 41.9742, -87.9073, "O'Hare International Airport"),
                new Airport(null, "ATL", 33.6407, -84.4277, "Hartsfield-Jackson Atlanta International Airport"),
                new Airport(null, "DFW", 32.8998, -97.0403, "Dallas/Fort Worth International Airport"),
                new Airport(null, "SFO", 37.6213, -122.379, "San Francisco International Airport"),
                new Airport(null, "DEN", 39.8561, -104.6737, "Denver International Airport"),
                new Airport(null, "LAS", 36.084, -115.1522, "McCarran International Airport"),
                new Airport(null, "SEA", 47.4502, -122.3088, "Seattle-Tacoma International Airport"),
                new Airport(null, "MCO", 28.4312, -81.3081, "Orlando International Airport")
        );

        airportRepository.saveAll(testAirports);

    }
    void populatePassengers() {

        List<Passenger> testPassengers = Arrays.asList(
                new Passenger(null, "Mateusz", "Borka", "537210099"),
                new Passenger(null, "Jane", "Smith", "987654321"),
                new Passenger(null, "Alice", "Johnson", "123456789"),
                new Passenger(null, "Bob", "Brown", "987654321"),
                new Passenger(null, "Emily", "Davis", "555555555"),
                new Passenger(null, "Michael", "Miller", "444444444"),
                new Passenger(null, "Olivia", "Wilson", "777777777"),
                new Passenger(null, "James", "Taylor", "222222222"),
                new Passenger(null, "Sophia", "Anderson", "888888888"),
                new Passenger(null, "William", "Thomas", "999999999")
        );
        passengerRepository.saveAll(testPassengers);
    }

}
