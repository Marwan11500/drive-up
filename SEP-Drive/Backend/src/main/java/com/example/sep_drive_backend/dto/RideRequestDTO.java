package com.example.sep_drive_backend.dto;

public class RideRequestDTO {
    private String benutzername;    // Der Kunde, der die Anfrage stellt
    private String startOrt;        // Startadresse
    private String zielOrt;         // Zieladresse
    private String fahrzeugKlasse;  // Fahrzeugklasse (z. B. klein, mittel, deluxe)

    private double startLat;        // Startpunkt Latitude (geografische Koordinaten)
    private double startLng;        // Startpunkt Longitude
    private double zielLat;         // Zielpunkt Latitude
    private double zielLng;         // Zielpunkt Longitude

    // === Getter und Setter ===

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getStartOrt() {
        return startOrt;
    }

    public void setStartOrt(String startOrt) {
        this.startOrt = startOrt;
    }

    public String getZielOrt() {
        return zielOrt;
    }

    public void setZielOrt(String zielOrt) {
        this.zielOrt = zielOrt;
    }

    public String getFahrzeugKlasse() {
        return fahrzeugKlasse;
    }

    public void setFahrzeugKlasse(String fahrzeugKlasse) {
        this.fahrzeugKlasse = fahrzeugKlasse;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLng() {
        return startLng;
    }

    public void setStartLng(double startLng) {
        this.startLng = startLng;
    }

    public double getZielLat() {
        return zielLat;
    }

    public void setZielLat(double zielLat) {
        this.zielLat = zielLat;
    }

    public double getZielLng() {
        return zielLng;
    }

    public void setZielLng(double zielLng) {
        this.zielLng = zielLng;
    }
}
