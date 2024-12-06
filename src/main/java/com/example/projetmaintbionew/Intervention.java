package com.example.projetmaintbionew;

public class Intervention {

    private int interventionId;

    private String designation;
    private String marque;

    private String lieuAff;

    private String description;

    private String transmis;

    private String valider;



    public Intervention(int interventionId, String designation, String marque, String lieuAff,  String description,
                        String transmis, String valider) {
        this.description = description;
        this.marque = marque;
        this.designation = designation;
        this.lieuAff = lieuAff;
        this.transmis = transmis;
        this.valider = valider;
        this.interventionId = interventionId;
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

    public String getTransmis() {
        return transmis;
    }

    public void setTransmis(String transmis) {
        this.transmis = transmis;
    }

    public String getValider() {
        return valider;
    }

    public void setValider(String valider) {
        this.valider = valider;
    }

    public int getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(int interventionId) {
        this.interventionId = interventionId;
    }
}
