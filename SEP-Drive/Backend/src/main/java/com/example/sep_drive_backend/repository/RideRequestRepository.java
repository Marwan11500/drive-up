package com.example.sep_drive_backend.repository;

import com.example.sep_drive_backend.models.Customer;
import com.example.sep_drive_backend.models.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {

    List<RideRequest> findByCustomer(Customer customer);
    Optional<RideRequest> findByCustomerUsernameAndCustomerActiveTrue(String username);

//    // Find ride requests by start latitude and longitude
//    List<RideRequest> findByStartLatitudeAndStartLongitude(double startLatitude, double startLongitude);
//
//
//    // Find ride requests by vehicle class
//    List<RideRequest> findByVehicleClass(VehicleClassEnum vehicleClass);
//
}
