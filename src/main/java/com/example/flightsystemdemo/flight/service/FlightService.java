package com.example.flightsystemdemo.flight.service;

import com.example.flightsystemdemo.airport.entity.Airport;
import com.example.flightsystemdemo.airport.repository.AirportRepository;
import com.example.flightsystemdemo.flight.dto.FlightRequestDto;
import com.example.flightsystemdemo.flight.dto.FlightResponseDto;
import com.example.flightsystemdemo.flight.entity.Flight;
import com.example.flightsystemdemo.flight.mapper.FlightMapper;
import com.example.flightsystemdemo.flight.repository.FlightRepository;
import com.example.flightsystemdemo.passenger.entity.Passenger;
import com.example.flightsystemdemo.passenger.repository.PassengerRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import java.util.function.UnaryOperator;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    private final FlightMapper mapper;
    private final PassengerRepository passengerRepository;
    private final AirportRepository airportRepository;


    public FlightService(FlightRepository flightRepository, FlightMapper mapper,
                         PassengerRepository passengerRepository,
                         AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.mapper = mapper;
        this.passengerRepository = passengerRepository;
        this.airportRepository = airportRepository;
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

    public List<Flight> getFilteredFlights(Long airportId, LocalDateTime minDateTime, LocalDateTime maxDateTime, Integer minSeats, Integer maxSeats) {
        // This definitely could be done better, using one SQL request, for example
        Optional<Airport> airport = Optional.empty();
        if (airportId != null) {

            airport = airportRepository.findById(airportId);
            if (!airport.isPresent()) {
                return new ArrayList<>();
            }
        }

        List<Flight> allFlights = flightRepository.findAll();

        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : allFlights) {
            if (airportId == null || flight.getFlightAirports().contains(airport.get())) {
                if (minDateTime == null || flight.getDepartureDateAndTime().isAfter(minDateTime) && maxDateTime == null || flight.getDepartureDateAndTime().isBefore(maxDateTime)) {
                    if (minSeats == null || flight.getFreePlaces() >= minSeats && maxSeats == null || flight.getFreePlaces() <= maxSeats) {
                        filteredFlights.add(flight);
                    }
                }
            }
        }

        return filteredFlights;


    }
}
