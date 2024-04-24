package com.example.flightsystemdemo.flight.service;

import com.example.flightsystemdemo.flight.dto.FlightRequestDto;
import com.example.flightsystemdemo.flight.dto.FlightResponseDto;
import com.example.flightsystemdemo.flight.entity.Flight;
import com.example.flightsystemdemo.flight.mapper.FlightMapper;
import com.example.flightsystemdemo.flight.repository.FlightRepository;
import com.example.flightsystemdemo.passenger.entity.Passenger;
import com.example.flightsystemdemo.passenger.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.UnaryOperator;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    private final FlightMapper mapper;
    private final PassengerRepository passengerRepository;


    public FlightService(FlightRepository flightRepository, FlightMapper mapper,
                         PassengerRepository passengerRepository) {
        this.flightRepository = flightRepository;
        this.mapper = mapper;
        this.passengerRepository = passengerRepository;
    }

    public Optional<FlightResponseDto> getFlightById(long id) {
        return flightRepository.findById(id).map(mapper::toDto);
    }

    public Optional<FlightResponseDto> createFlight(FlightRequestDto flightRequestDto) {

        Flight entity = mapper.toEntity(flightRequestDto, new Flight());
        entity.setPassengers(new ArrayList<>());

        final Flight savedFlight = flightRepository.save(entity);
        final Optional<Flight> createdFlight = flightRepository.findById(savedFlight.getId());

        if (createdFlight.get().getFlightAirports().size() < 2){
            return Optional.empty();
        }

        return createdFlight.map(mapper::toDto);
    }

    public Optional<FlightResponseDto> updateFlight(long flightId, FlightRequestDto flightRequestDto) {

        final UnaryOperator<Flight> flightEntityMapper = existingPassenger -> mapper.toEntity(flightRequestDto, existingPassenger);

        final Optional<Flight> existingFlightEntity = flightRepository.findById(flightId);


        final Flight flightToUpdate = flightEntityMapper.apply(existingFlightEntity.get());

        flightRepository.save(flightToUpdate);

        final Optional<Flight> updatedFlight = flightRepository.findById(flightToUpdate.getId());

        return updatedFlight.map(mapper::toDto);


    }

    public boolean deleteFlight(long flightId) {
        Optional<Flight> existingFlight = flightRepository.findById(flightId);

        if (!existingFlight.isPresent()) {
            return false;
        }

        flightRepository.deleteById(flightId);

        return true;
    }


    public Optional<FlightResponseDto> addPassenger(long flightId, long passengerId) {

        Optional<Flight> existingFlight = flightRepository.findById(flightId);
        Optional<Passenger> existingPassenger = passengerRepository.findById(passengerId);

        if (!existingFlight.isPresent() || !existingPassenger.isPresent()) {
            return Optional.empty();
        }

        if (existingFlight.get().getFreePlaces() < 1 || existingFlight.get().getPassengers().contains(existingPassenger.get())){
            return Optional.empty();
        }

        existingFlight.get().addPassenger(existingPassenger.get());
        flightRepository.save(existingFlight.get());
        return existingFlight.map(mapper::toDto);

    }

    public Optional<FlightResponseDto> removePassenger(long flightId, long passengerId){

        Optional<Flight> existingFlight = flightRepository.findById(flightId);
        Optional<Passenger> existingPassenger = passengerRepository.findById(passengerId);

        if (!existingFlight.isPresent() || !existingPassenger.isPresent()) {
            return Optional.empty();
        }

        if (!existingFlight.get().getPassengers().contains(existingPassenger.get())){
            return Optional.empty();
        }

        existingFlight.get().removePassenger(existingPassenger.get());
        flightRepository.save(existingFlight.get());
        return existingFlight.map(mapper::toDto);


    }
}
