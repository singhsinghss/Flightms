package com.Flights.Flightms.Model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FlightDTO {

    private Long flight_id;
    private String flight_number;
    private String departure;
    private String arrival;
    private LocalDateTime departure_time;
    private LocalDateTime arrival_time;
    private Double price;
    private Integer available_seats;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
