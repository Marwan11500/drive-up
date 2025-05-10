package com.example.sep_drive_backend.models;

import com.example.sep_drive_backend.constants.VehicleClassEnum;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;

@Entity
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "customer_username", referencedColumnName = "username", nullable = false)
    private Customer customer; // This will be the reference to the Customer entity via 'username'


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public RideRequest() {}
    public RideRequest(Long id, String startAddress, String destinationAddress, VehicleClassEnum vehicleClass, Customer customer) {
        this.id = id;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.vehicleClass = vehicleClass;
        this.customer = customer;
    }

    @Column
    private String startAddress;
    @Column
    private String destinationAddress;


    @Column
    @Enumerated(EnumType.STRING)
    private VehicleClassEnum vehicleClass; // SMALL, MEDIUM, LARGE


    // === GETTERS & SETTERS ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }


    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }


    public VehicleClassEnum getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(VehicleClassEnum vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

}