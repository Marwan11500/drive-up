package com.example.sep_drive_backend.dto;

import com.example.sep_drive_backend.constants.RoleEnum;
import com.example.sep_drive_backend.constants.VehicleClassEnum;

import java.util.Date;


public class RegistrationRequest {

        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private Date birthDate;
        private RoleEnum role;
        private String profilePicture;
        private VehicleClassEnum vehicleClass;


    public VehicleClassEnum getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(VehicleClassEnum vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public void setProfilePicture(String profilePicture) {
                this.profilePicture = profilePicture;
        }

        public String getProfilePicture() {
                return profilePicture;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public Date getBirthDate() {
                return birthDate;
        }

        public void setBirthDate(Date birthDate) {
                this.birthDate = birthDate;
        }

        public RoleEnum getRole() {
                return role;
        }

        public void setRole(RoleEnum role) {
                this.role = role;
        }

}
