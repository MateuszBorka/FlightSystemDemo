package com.example.flightsystemdemo.passenger.service;

import com.example.flightsystemdemo.passenger.dto.PassengerRequestDto;
import com.example.flightsystemdemo.passenger.dto.PassengerResponseDto;
import com.example.flightsystemdemo.passenger.entity.Passenger;
import com.example.flightsystemdemo.passenger.mapper.PassengerMapper;
import com.example.flightsystemdemo.passenger.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    private final PassengerMapper mapper;


    public Optional<PassengerResponseDto> getPassengerById(long id) {
        return passengerRepository.findById(id).map(mapper::toDto);
    }

    public Optional<PassengerResponseDto> createPassenger(PassengerRequestDto passengerRequestDto) {

        Passenger entity = mapper.toEntity(passengerRequestDto, new Passenger());

        final Passenger savedPassenger = passengerRepository.save(entity);
        final Optional<Passenger> createdPassenger = passengerRepository.findById(savedPassenger.getId());

        return createdPassenger.map(mapper::toDto);
    }

    public Optional<PassengerResponseDto> updatePassenger(long passengerId, PassengerRequestDto passengerRequestDto) {

        final UnaryOperator<Passenger> passengerEntityMapper = existingPassenger -> mapper.toEntity(passengerRequestDto, existingPassenger);

        final Optional<Passenger> existingPassengerEntity = passengerRepository.findById(passengerId);


        final Passenger passengerToUpdate = passengerEntityMapper.apply(existingPassengerEntity.get());

        passengerRepository.save(passengerToUpdate);

        final Optional<Passenger> updatedPassenger = passengerRepository.findById(passengerToUpdate.getId());

        return updatedPassenger.map(mapper::toDto);


    }

    public boolean deletePassenger(long passengerId) {
        Optional<Passenger> existingPassenger = passengerRepository.findById(passengerId);

        if (!existingPassenger.isPresent()) {
            return false;
        }

        passengerRepository.deleteById(passengerId);

        return true;
    }

    public List<Passenger> findByIds(List<Long> passengerIds) {
        return passengerRepository.findAllById(passengerIds);
    }

    public List<PassengerResponseDto> getAllPassengers() {
        return passengerRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}