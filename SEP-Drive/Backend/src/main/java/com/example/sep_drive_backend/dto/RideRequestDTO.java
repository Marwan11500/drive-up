package com.example.sep_drive_backend.dto;

import com.example.sep_drive_backend.constants.VehicleClassEnum;
import com.example.sep_drive_backend.models.Customer;
import com.example.sep_drive_backend.models.RideRequest;

public class RideRequestDTO {

    public RideRequestDTO() {
    }
    public RideRequestDTO(RideRequest request) {
        this.userName = request.getCustomer().getUsername();
        this.startAddress = request.getStartAddress();
        this.destinationAddress = request.getDestinationAddress();
        this.vehicleClass = request.getVehicleClass();
    }


    private String userName;
    private String startAddress;
    private String destinationAddress;
    private VehicleClassEnum vehicleClass;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartAddress() {
        return this.startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getDestinationAddress() {
        return this.destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public VehicleClassEnum getVehicleClass() {
        return this.vehicleClass;
    }

    public void setVehicleClass(VehicleClassEnum vehicleClass) {
        this.vehicleClass = vehicleClass;
    }


}
