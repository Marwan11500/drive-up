package com.example.sep_drive_backend.repository;

import com.example.sep_drive_backend.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByUsername(String username);
    Optional<Driver> findByEmail(String email);
}

