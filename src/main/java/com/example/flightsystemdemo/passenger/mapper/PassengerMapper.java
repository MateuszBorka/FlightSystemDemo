package com.example.flightsystemdemo.passenger.mapper;

import com.example.flightsystemdemo.passenger.dto.PassengerRequestDto;
import com.example.flightsystemdemo.passenger.dto.PassengerResponseDto;
import com.example.flightsystemdemo.passenger.entity.Passenger;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@RequiredArgsConstructor
@Mapper
public abstract class PassengerMapper {

    public abstract Passenger toEntity(PassengerRequestDto dto, @MappingTarget Passenger entity);

    public abstract PassengerResponseDto toDto(Passenger entity);
}
