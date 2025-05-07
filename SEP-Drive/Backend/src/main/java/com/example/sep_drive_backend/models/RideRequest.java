package com.example.sep_drive_backend.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String benutzername; // Der Kunde, der die Anfrage stellt

    private String startOrt;      // Startadresse
    private String zielOrt;       // Zieladresse

    private String fahrzeugKlasse; // klein, mittel, deluxe

    private boolean aktiv; // Ob die Anfrage aktiv ist

    private LocalDateTime erstelltAm;

    // === GETTER & SETTER ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public LocalDateTime getErstelltAm() {
        return erstelltAm;
    }

    public void setErstelltAm(LocalDateTime erstelltAm) {
        this.erstelltAm = erstelltAm;
    }
}
