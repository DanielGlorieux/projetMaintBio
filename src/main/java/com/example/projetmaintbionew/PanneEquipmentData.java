package com.example.projetmaintbionew;

public class PanneEquipmentData {
    //private int idPanne;
    private String description;
    //private Integer idEquipment;
    // Can be null
    private  String type;

    private String statut;
    private String marque;
    private String designation;

    private String lieuAff;

    public PanneEquipmentData(String designation, String marque, String description, String statut,String type) {
        this.description = description;
        this.marque = marque;
        this.designation = designation;
        this.statut = statut;
        this.type = type;
    }


    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

