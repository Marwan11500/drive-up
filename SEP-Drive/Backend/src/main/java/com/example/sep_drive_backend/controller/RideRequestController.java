package com.example.sep_drive_backend.controller;


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
    public RideRequest create(@RequestBody RideRequest request) {
        return service.createRideRequest(request);
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
