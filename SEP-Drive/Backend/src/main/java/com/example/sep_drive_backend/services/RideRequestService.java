package com.example.sep_drive_backend.services;


import com.example.sep_drive_backend.models.RideRequest;
import com.example.sep_drive_backend.repository.RideRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideRequestService {

    @Autowired
    private RideRequestRepository repository;

    public RideRequest createRideRequest(RideRequest request) {
        // Prüfen, ob Kunde schon eine aktive Anfrage hat
        List<RideRequest> existing = repository.findByBenutzernameAndAktivTrue(request.getBenutzername());
        if (!existing.isEmpty()) {
            throw new IllegalStateException("Du hast bereits eine aktive Fahranfrage.");
        }

        request.setErstelltAm(LocalDateTime.now());
        request.setAktiv(true);
        return repository.save(request);
    }

    public List<RideRequest> getAllRequests() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
