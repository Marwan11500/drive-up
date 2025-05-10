package com.example.sep_drive_backend.repository;

import com.example.sep_drive_backend.models.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<users, Long> {
    Optional<users> findByUsername(String username);
}
