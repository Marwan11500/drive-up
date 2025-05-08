package com.example.sep_drive_backend.services;

import com.example.sep_drive_backend.models.RideRequest;
import com.example.sep_drive_backend.repository.RideRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class RideRequestService {

    @Autowired
    private RideRequestRepository repository;

    private final String API_KEY = "DEIN_OPENROUTESERVICE_API_KEY"; // TODO: extern speichern
    private final String ROUTE_URL = "https://api.openrouteservice.org/v2/directions/driving-car";

    public RideRequest createRideRequest(RideRequest request) {
        // 1. Nur eine aktive Anfrage pro Nutzer zulassen
        List<RideRequest> existing = repository.findByBenutzernameAndAktivTrue(request.getBenutzername());
        if (!existing.isEmpty()) {
            throw new IllegalStateException("Du hast bereits eine aktive Fahranfrage.");
        }

        // 2. Route berechnen
        double[][] coordinates = buildCoordinatesArray(request);
        Map<String, Object> routeInfo = fetchRouteInfo(coordinates);

        double distanzKm = ((Number)((Map<?, ?>) routeInfo.get("summary")).get("distance")).doubleValue() / 1000.0;
        double dauerMin = ((Number)((Map<?, ?>) routeInfo.get("summary")).get("duration")).doubleValue() / 60.0;

        // 3. Fahranfrage speichern
        request.setDistanzKm(distanzKm);
        request.setDauerMin(dauerMin);
        request.setAktiv(true);
        request.setErstelltAm(LocalDateTime.now());

        return repository.save(request);
    }

    private double[][] buildCoordinatesArray(RideRequest request) {
        // Start → Zwischenstopps → Ziel
        // Beispiel ohne Zwischenstopps
        return new double[][] {
                { request.getStartLng(), request.getStartLat() },
                { request.getZielLng(), request.getZielLat() }
        };
    }

    private Map<String, Object> fetchRouteInfo(double[][] coords) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> body = Map.of("coordinates", coords);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("Authorization", API_KEY);
        headers.set("Content-Type", "application/json");

        var entity = new org.springframework.http.HttpEntity<>(body, headers);

        var response = restTemplate.postForEntity(ROUTE_URL, entity, Map.class);
        List<?> routes = (List<?>) response.getBody().get("routes");
        return (Map<String, Object>) ((Map<?, ?>) routes.get(0)).get("summary");
    }

    public List<RideRequest> getAllRequests() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
