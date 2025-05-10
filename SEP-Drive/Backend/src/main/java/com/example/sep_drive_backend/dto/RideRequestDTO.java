package com.example.sep_drive_backend.dto;

import com.example.sep_drive_backend.constants.VehicleClassEnum;
import com.example.sep_drive_backend.models.Customer;

public class RideRequestDTO {
    private Customer customer;
    private String startAddress;
    private String destinationAddress;
    private VehicleClassEnum vehicleClass;



    // Getter und Setter
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getStartLocation() { return startAddress; }
    public void setStartLocation(String startLocation) { this.startAddress = startAddress; }

    public String getDestinationLocation() { return destinationAddress; }
    public void setDestinationLocation(String destinationLocation) { this.destinationAddress = destinationAddress; }

    public VehicleClassEnum getVehicleClass() { return vehicleClass; }
    public void setVehicleClass(VehicleClassEnum vehicleClass) { this.vehicleClass = vehicleClass; }

}
