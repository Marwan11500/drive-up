package com.example.sep_drive_backend.controller;

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
    private RideRequestService service;

    // Neue Fahranfrage erstellen
    @PostMapping
    public RideRequest create(@RequestBody RideRequestDTO requestDTO) {
        // Hier wird RideRequestDTO in RideRequest umgewandelt
        RideRequest rideRequest = new RideRequest();
        rideRequest.setBenutzername(requestDTO.getBenutzername());
        rideRequest.setStartOrt(requestDTO.getStartOrt());
        rideRequest.setZielOrt(requestDTO.getZielOrt());
        rideRequest.setFahrzeugKlasse(requestDTO.getFahrzeugKlasse());
        rideRequest.setStartLat(requestDTO.getStartLat());
        rideRequest.setStartLng(requestDTO.getStartLng());
        rideRequest.setZielLat(requestDTO.getZielLat());
        rideRequest.setZielLng(requestDTO.getZielLng());

        return service.createRideRequest(rideRequest);
    }

    // Alle Fahranfragen auflisten
    @GetMapping
    public List<RideRequest> list() {
        return service.getAllRequests();
    }

    // Fahranfrage per ID löschen
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
