    package com.example.flightsystemdemo.flight.entity
            ;


    import com.example.flightsystemdemo.airport.entity.Airport;
    import com.example.flightsystemdemo.passenger.entity.Passenger;
    import com.example.flightsystemdemo.validation.ValidationConsts;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Future;
    import jakarta.validation.constraints.NotNull;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import org.hibernate.validator.constraints.Range;

    import java.time.LocalDateTime;
    import java.util.List;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Flight {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // I assume we can count it as a number of flight

        @Range(
                min = ValidationConsts.MIN_FREE_PLACES,
                max = ValidationConsts.MAX_FREE_PLACES,
                message = "Number of free places must be between {min} and {max}"
        )
        private Integer freePlaces;

        @ManyToMany
        @JoinTable(
                name = "flight_airport",
                joinColumns = @JoinColumn(name = "flight_id"),
                inverseJoinColumns = @JoinColumn(name = "airport_id")
        )
        private List<Airport> flightAirports;


        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Passenger> passengers ;

        @Future(message = "Departure date and time must be in the future")
        @NotNull
        LocalDateTime departureDateAndTime;


        public void addPassenger(Passenger passenger) {
            this.passengers.add(passenger);
            this.freePlaces--;
        }


        public void removePassenger(Passenger passenger) {
            this.passengers.remove(passenger);
            this.freePlaces++;
        }
    }
