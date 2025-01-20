package com.Flights.Flightms.Controller;

import com.Flights.Flightms.Model.FlightDTO;
import com.Flights.Flightms.Service.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightServiceImpl flightService;

    @PostMapping
    public FlightDTO addNewFlight(@RequestBody FlightDTO flight) {
        return flightService.addNewFlight(flight);
    }

    @GetMapping("/{flight_id}")
    public FlightDTO getFlightById(@PathVariable Long flight_id) {
        return flightService.getFlightById(flight_id);
    }
    @GetMapping
    public ResponseEntity<List<FlightDTO>> getAllFlight() {
        List<FlightDTO> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @PutMapping("/{flight_id}")
    public FlightDTO updateFlightData(@PathVariable Long flight_id, @RequestBody FlightDTO flight) {
        return flightService.updateFlightById(flight_id, flight);
    }
    @DeleteMapping("/{flight_id}")
    public String deleteFlightById(@PathVariable Long flight_id) {
        return flightService.deleteFlightData(flight_id);
    }
}
