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
        this.startLatitude = request.getStartLatitude();
        this.startLongitude = request.getStartLongitude();
        this.destinationLatitude = request.getDestinationLatitude();
        this.destinationLongitude = request.getDestinationLongitude();
    }


    private String userName;


    private String startAddress;

    private Double startLatitude;
    private Double startLongitude;

    private String destinationAddress;

    private Double destinationLatitude;
    private Double destinationLongitude;


    private VehicleClassEnum vehicleClass;


    public Double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public Double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }



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
