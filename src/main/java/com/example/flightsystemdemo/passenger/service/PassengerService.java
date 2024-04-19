package com.example.flightsystemdemo.passenger.service;

import com.example.flightsystemdemo.passenger.dto.PassengerRequestDto;
import com.example.flightsystemdemo.passenger.dto.PassengerResponseDto;
import com.example.flightsystemdemo.passenger.entity.Passenger;
import com.example.flightsystemdemo.passenger.mapper.PassengerMapper;
import com.example.flightsystemdemo.passenger.repository.PassengerRepository;
import io.micrometer.observation.ObservationFilter;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper mapper;


    public Optional<PassengerResponseDto> getPassengerById(long id) {
        return passengerRepository.findById(id).map(mapper::toDto);
    }

    public Optional<PassengerResponseDto> createPassenger(@NotNull PassengerRequestDto passengerRequestDto){

        Passenger entity = mapper.toEntity(passengerRequestDto, new Passenger());

        final Passenger savedPassenger = passengerRepository.save(entity);
        final Optional<Passenger> createdPassenger = passengerRepository.findById(savedPassenger.getId());

        return createdPassenger.map(mapper::toDto);
    }
}
