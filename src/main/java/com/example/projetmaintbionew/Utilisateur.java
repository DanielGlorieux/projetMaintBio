package com.example.projetmaintbionew;

public class Utilisateur {
    private String nom;
    private String code;
    private String prof;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String code, String prof) {
        this.nom = nom;
        this.code = code;
        this.prof = prof;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }
}
