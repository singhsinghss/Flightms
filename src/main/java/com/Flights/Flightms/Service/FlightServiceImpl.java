package com.Flights.Flightms.Service;


import com.Flights.Flightms.Model.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class FlightServiceImpl {
    @Autowired
    private final RestTemplate restTemplate;

    @Value("${DB.SERVICE.URL}")
    private final String DB_SERVICE_URL;

    public FlightServiceImpl(RestTemplate restTemplate, @Value("${DB.SERVICE.URL}") String DBMicroserviceUrl) {
        this.restTemplate = restTemplate;
        DB_SERVICE_URL = DBMicroserviceUrl;
    }

    public FlightDTO addNewFlight(FlightDTO flight) {
        return restTemplate.postForObject(DB_SERVICE_URL, flight, FlightDTO.class);
    }

    public FlightDTO getFlightById(Long flightId) {
        System.out.println("Get Flight url: " + DB_SERVICE_URL + "/" + flightId);
        return restTemplate.getForObject(DB_SERVICE_URL + "/" + flightId, FlightDTO.class);
    }
    public List<FlightDTO> getAllFlights() {
        System.out.println("Get All Flight url: " + DB_SERVICE_URL);
        ResponseEntity<FlightDTO[]> response = restTemplate.getForEntity(DB_SERVICE_URL, FlightDTO[].class);

        return Arrays.asList(response.getBody());
    }

    public FlightDTO updateFlightById(Long flightId, FlightDTO flight) {
        FlightDTO existingFlight = restTemplate.getForObject(DB_SERVICE_URL + "/" + flightId, FlightDTO.class);

        if (existingFlight == null) {
            throw new RuntimeException("Flight not found with ID: " + flightId);
        }

        existingFlight.setDeparture(flight.getDeparture());
        existingFlight.setArrival(flight.getArrival());
        existingFlight.setDeparture_time(flight.getDeparture_time());
        existingFlight.setArrival_time(flight.getArrival_time());
        existingFlight.setPrice(flight.getPrice());
        existingFlight.setAvailable_seats(flight.getAvailable_seats());
        existingFlight.setFlight_number(flight.getFlight_number());
        existingFlight.setUpdated_at(LocalDateTime.now());
        String updateUrl = DB_SERVICE_URL + "/" + flightId;
        restTemplate.put(updateUrl, existingFlight);

        return existingFlight;
    }

    public String deleteFlightData(Long flightId) {
        restTemplate.delete(DB_SERVICE_URL + "/" + flightId);
        return "Flight with ID " + flightId + " deleted successfully.";
    }

}
