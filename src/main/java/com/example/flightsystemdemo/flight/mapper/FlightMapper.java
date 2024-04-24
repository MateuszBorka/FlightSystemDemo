package com.example.flightsystemdemo.flight.mapper;

import com.example.flightsystemdemo.airport.entity.Airport;
import com.example.flightsystemdemo.airport.service.AirportService;
import com.example.flightsystemdemo.flight.dto.FlightRequestDto;
import com.example.flightsystemdemo.flight.dto.FlightResponseDto;
import com.example.flightsystemdemo.flight.entity.Flight;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class FlightMapper {


    @Autowired
    private AirportService airportService;


    @Mapping(target = "passengers", ignore = true)
    @Mapping(target = "flightAirports", expression = "java(mapAirportIdsToEntities(dto.getFlightAirports()))")
    public abstract Flight toEntity(FlightRequestDto dto, @MappingTarget Flight entity);

    public abstract FlightResponseDto toDto(Flight entity);


    protected List<Airport> mapAirportIdsToEntities(List<Long> airportIds) {
        return airportService.createFlightAirports(airportIds);
    }


}
