package com.example.sep_drive_backend.models;

import com.example.sep_drive_backend.constants.RoleEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
//@DiscriminatorValue("Customer")
public class Customer extends users {
    private float rating = 0;
    private int totalRides = 0;


    public Customer() {}

    public Customer(String username, String firstName, String lastName, String email ,Date birthDate, String password, RoleEnum role, float rating, int totalRides) {
        super(username, firstName, lastName, email, birthDate, password, role);
        this.rating = rating;
        this.totalRides = totalRides;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalRides() {
        return totalRides;
    }

    public void setTotalRides(int totalRides) {
        this.totalRides = totalRides;
    }
}
