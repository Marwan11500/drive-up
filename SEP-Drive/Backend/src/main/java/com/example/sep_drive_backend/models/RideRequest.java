package com.example.sep_drive_backend.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String benutzername; // Der Kunde, der die Anfrage stellt

    private String startOrt;// Startadresse
    private double startLat;
    private double startLng;

    private String zielOrt;       // Zieladresse
    private double zielLat;
    private double zielLng;

    private String fahrzeugKlasse; // klein, mittel, deluxe

    private boolean aktiv; // Ob die Anfrage aktiv ist

    private LocalDateTime erstelltAm;

    private double distanzKm;
    private double dauerMin;

    @Lob
    private String zwischenstoppsJson;

    // === GETTER & SETTER ===

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getBenutzername() { return benutzername; }
    public void setBenutzername(String benutzername) { this.benutzername = benutzername; }


    public String getStartOrt() { return startOrt;}
    public void setStartOrt(String startOrt) { this.startOrt = startOrt; }

    public double getStartLat() { return startLat; }
    public void setStartLat(double startLat) { this.startLat = startLat; }

    public double getStartLng() { return startLng; }
    public void setStartLng(double startLng) { this.startLng = startLng; }


    public String getZielOrt() { return zielOrt; }
    public void setZielOrt(String zielOrt) { this.zielOrt = zielOrt; }

    public double getZielLat() { return zielLat; }
    public void setZielLat(double zielLat) { this.zielLat = zielLat; }

    public double getZielLng() { return zielLng; }
    public void setZielLng(double zielLng) { this.zielLng = zielLng; }


    public String getFahrzeugKlasse() { return fahrzeugKlasse; }
    public void setFahrzeugKlasse(String fahrzeugKlasse) { this.fahrzeugKlasse = fahrzeugKlasse; }

    public boolean isAktiv() { return aktiv;}
    public void setAktiv(boolean aktiv) { this.aktiv = aktiv; }

    public LocalDateTime getErstelltAm() { return erstelltAm; }
    public void setErstelltAm(LocalDateTime erstelltAm) { this.erstelltAm = erstelltAm; }

    public double getDistanzKm() { return distanzKm; }
    public void setDistanzKm(double distanzKm) { this.distanzKm = distanzKm; }

    public double getDauerMin() { return dauerMin; }
    public void setDauerMin(double dauerMin) { this.dauerMin = dauerMin; }

    public String getZwischenstopps() { return zwischenstoppsJson; }
    public void setZwischenstopps(String  zwischenstopps) { this.zwischenstoppsJson = zwischenstopps; }
}
