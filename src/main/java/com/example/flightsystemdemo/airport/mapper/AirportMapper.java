package com.example.flightsystemdemo.airport.mapper;

import com.example.flightsystemdemo.airport.dto.AirportRequestDto;
import com.example.flightsystemdemo.airport.dto.AirportResponseDto;
import com.example.flightsystemdemo.airport.entity.Airport;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;

@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class AirportMapper {

    public abstract Airport toEntity(AirportRequestDto dto, @MappingTarget Airport entity);

    public abstract AirportResponseDto toDto(Airport entity);
}
