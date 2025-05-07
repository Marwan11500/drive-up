package com.example.sep_drive_backend.repository;

import com.example.sep_drive_backend.dto.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
    List<RideRequest> findByBenutzernameAndAktivTrue(String benutzername);
}

