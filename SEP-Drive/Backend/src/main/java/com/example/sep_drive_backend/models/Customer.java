package com.example.sep_drive_backend.models;

import com.example.sep_drive_backend.constants.RoleEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Customer extends users {


    public Customer() {}

    public Customer(String username, String firstName, String lastName, String email ,Date birthDate, String password, RoleEnum role, String profilePicture) {
        super(username, firstName, lastName, email, birthDate, password, role, profilePicture);

    }

}
