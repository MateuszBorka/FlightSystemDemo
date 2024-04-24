package com.example.flightsystemdemo.passenger.mapper;

import com.example.flightsystemdemo.passenger.dto.PassengerRequestDto;
import com.example.flightsystemdemo.passenger.dto.PassengerResponseDto;
import com.example.flightsystemdemo.passenger.entity.Passenger;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;

@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class PassengerMapper {

//    @Autowired
//    private PassengerService passengerService;
//
//    private final PassengerRepository passengerRepository;

    public abstract Passenger toEntity(PassengerRequestDto dto, @MappingTarget Passenger entity);

    public abstract PassengerResponseDto toDto(Passenger entity);

//    protected List<Passenger> mapPassengerIdsToEntities(List<Long> passengerIds) {
//        return passengerService.findByIds(passengerIds);
//    }
}
