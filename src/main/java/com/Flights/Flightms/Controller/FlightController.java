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

    @GetMapping("/{flightId}")
    public FlightDTO getFlightById(@PathVariable Long flightId) {
        return flightService.getFlightById(flightId);
    }
    @GetMapping
    public ResponseEntity<List<FlightDTO>> getAllFlight() {
        List<FlightDTO> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @PutMapping("/{flightId}")
    public FlightDTO updateFlightData(@PathVariable Long flightId, @RequestBody FlightDTO flight) {
        return flightService.updateFlightById(flightId, flight);
    }
    @DeleteMapping("/{flight_id}")
    public String deleteFlightById(@PathVariable Long flightId) {
        return flightService.deleteFlightData(flightId);
    }
}
