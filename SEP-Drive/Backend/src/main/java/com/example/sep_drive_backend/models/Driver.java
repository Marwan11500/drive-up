package com.example.sep_drive_backend.models;

import com.example.sep_drive_backend.constants.RoleEnum;
import com.example.sep_drive_backend.constants.VehicleClassEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Driver extends users {


    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private VehicleClassEnum vehicleClass;

    public Driver() {}

    public Driver(String username, String firstName, String lastName, String email ,Date birthDate, String password, RoleEnum role, String profilePicture, VehicleClassEnum vehicleClass) {
        super(username, firstName, lastName, email, birthDate, password, role, profilePicture);

        this.vehicleClass = vehicleClass;

    }

    public VehicleClassEnum getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(VehicleClassEnum vehicleClass) {
        this.vehicleClass = vehicleClass;
    }
}
