package com.example.sep_drive_backend.dto;

import com.example.sep_drive_backend.constants.RoleEnum;

import java.util.Date;

import java.util.Date;

public class UserProfileResponse {
    private String username;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private float rating;
    private int totalRides;
    private String vehicleClass; // Only for drivers

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public int getTotalRides() { return totalRides; }
    public void setTotalRides(int totalRides) { this.totalRides = totalRides; }

    public String getVehicleClass() { return vehicleClass; }
    public void setVehicleClass(String vehicleClass) { this.vehicleClass = vehicleClass; }
}
