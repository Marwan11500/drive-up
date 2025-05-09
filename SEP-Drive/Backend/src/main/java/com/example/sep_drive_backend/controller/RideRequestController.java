package com.example.sep_drive_backend.controller;

import com.example.sep_drive_backend.constants.VehicleClassEnum;
import com.example.sep_drive_backend.dto.RideRequestDTO;
import com.example.sep_drive_backend.models.RideRequest;
import com.example.sep_drive_backend.services.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ride-requests")
public class RideRequestController {

    @Autowired
    private RideRequestService rideRequestService;


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public RideRequest createRideRequest(@RequestBody RideRequestDTO dto) {


        return rideRequestService.createRideRequestFromDTO(dto);
    }
    // Neue Methode, um Fahranfragen basierend auf der Fahrzeugklasse zu erhalten
    @GetMapping("/by-vehicle-class/{vehicleClass}")
    public List<RideRequest> getRequestsByVehicleClass(@PathVariable VehicleClassEnum vehicleClass) {
        return rideRequestService.getRequestsByVehicleClass(vehicleClass);
    }

    @GetMapping
    public List<RideRequest> getAllRideRequests() {
        return rideRequestService.getAllRequests();
    }

    @DeleteMapping("/{id}")
    public void deleteRideRequest(@PathVariable Long id) {
        rideRequestService.deleteById(id);
    }
}
