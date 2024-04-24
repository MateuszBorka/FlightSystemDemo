package com.example.flightsystemdemo.airport.service;

import com.example.flightsystemdemo.airport.dto.AirportRequestDto;
import com.example.flightsystemdemo.airport.dto.AirportResponseDto;
import com.example.flightsystemdemo.airport.entity.Airport;
import com.example.flightsystemdemo.airport.mapper.AirportMapper;
import com.example.flightsystemdemo.airport.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    private final AirportMapper mapper;


    public Optional<AirportResponseDto> getAirportById(long id) {
        return airportRepository.findById(id).map(mapper::toDto);
    }

    public Optional<AirportResponseDto> createAirport(AirportRequestDto airportRequestDto) {

        Airport entity = mapper.toEntity(airportRequestDto, new Airport());

        final Airport savedAirport = airportRepository.save(entity);
        final Optional<Airport> createdAirport = airportRepository.findById(savedAirport.getId());

        return createdAirport.map(mapper::toDto);
    }

    public Optional<AirportResponseDto> updateAirport(long airportId, AirportRequestDto airportRequestDto) {

        final UnaryOperator<Airport> AirportEntityMapper = existingAirport -> mapper.toEntity(airportRequestDto, existingAirport);

        final Optional<Airport> existingAirportEntity = airportRepository.findById(airportId);


        final Airport airportToUpdate = AirportEntityMapper.apply(existingAirportEntity.get());

        airportRepository.save(airportToUpdate);

        final Optional<Airport> updatedAirport = airportRepository.findById(airportToUpdate.getId());

        return updatedAirport.map(mapper::toDto);


    }

    public boolean deleteAirport(long airportId) {
        Optional<Airport> existingPassenger = airportRepository.findById(airportId);

        if (!existingPassenger.isPresent()) {
            return false;
        }

        airportRepository.deleteById(airportId);

        return true;
    }



    public List<Airport> createFlightAirports(List<Long> airportIds) {
        return airportRepository.findAllById(airportIds);
    }
}
