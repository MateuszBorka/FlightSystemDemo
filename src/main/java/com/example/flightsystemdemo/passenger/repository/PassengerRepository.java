package com.example.flightsystemdemo.passenger.repository;

import com.example.flightsystemdemo.passenger.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
