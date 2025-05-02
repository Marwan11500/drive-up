package com.example.sep_drive_backend.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    @OneToOne
    private users user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    public VerificationToken() {
    }

    public VerificationToken(users user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        this.expiryDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000); // 24h expiry
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public users getUser() {
        return user;
    }

    public void setUser(users user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}