package com.example.sep_drive_backend.repository;

import com.example.sep_drive_backend.models.RideRequest;
import com.example.sep_drive_backend.constants.VehicleClassEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {

    // Find active ride requests for a specific username
    List<RideRequest> findByUsernameAndActiveTrue(String username);

    // Find ride requests by start latitude and longitude
    List<RideRequest> findByStartLatAndStartLng(double startLat, double startLng);

    // Find ride requests by vehicle class
    List<RideRequest> findByVehicleClass(VehicleClassEnum vehicleClass);
}
