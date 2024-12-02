package com.example.projetmaintbionew;

public class Intervention {

    private String designation;
    private String marque;

    private String lieuAff;

    private String description;




    public Intervention(String designation, String marque, String lieuAff,  String description) {
        this.description = description;
        this.marque = marque;
        this.designation = designation;
        this.lieuAff = lieuAff;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getLieuAff() {
        return lieuAff;
    }

    public void setLieuAff(String lieuAff) {
        this.lieuAff = lieuAff;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
