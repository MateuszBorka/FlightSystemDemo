package com.example.flightsystemdemo.flight.repository;

import com.example.flightsystemdemo.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface FlightRepository extends JpaRepository<Flight, Long> {

}
