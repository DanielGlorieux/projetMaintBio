package com.example.projetmaintbionew;

public class PanneEquipmentData {
    //private int idPanne;

    Boolean transmitted;

    private int interventionId;
    private String description;
    //private Integer idEquipment;
    // Can be null
    private  String type;

    private String statut;
    private String marque;
    private String designation;

    private String rappInterv;

    private String lieuAff;

    private String prevDate;

    private String intervenantNom;


    public PanneEquipmentData(String designation, String marque, String description, String statut,String type) {
        this.description = description;
        this.marque = marque;
        this.designation = designation;
        this.statut = statut;
        this.type = type;
    }

    public PanneEquipmentData(int interventionId, String designation, String marque, String description, String statut,String type, String lieuAff) {
        this.description = description;
        this.marque = marque;
        this.designation = designation;
        this.statut = statut;
        this.type = type;
        this.lieuAff = lieuAff;
        this.interventionId = interventionId;
    }

    public PanneEquipmentData(int interventionId, String designation, String marque, String description, String statut,String type, String lieuAff,
                              boolean transmitted) {
        this.description = description;
        this.marque = marque;
        this.designation = designation;
        this.statut = statut;
        this.type = type;
        this.lieuAff = lieuAff;
        this.interventionId = interventionId;
        this.transmitted = transmitted;
    }

    public PanneEquipmentData(int interventionId, String designation, String marque, String description, String statut,String type, String lieuAff,
                              boolean transmitted, String prevDate) {
        this.description = description;
        this.marque = marque;
        this.designation = designation;
        this.statut = statut;
        this.type = type;
        this.lieuAff = lieuAff;
        this.interventionId = interventionId;
        this.transmitted = transmitted;
        this.prevDate = prevDate;
    }

    public PanneEquipmentData(String designation, String marque, String description, String statut,String type, String rappInterv) {
        this.description = description;
        this.marque = marque;
        this.designation = designation;
        this.statut = statut;
        this.type = type;
        //this.lieuAff = lieuAff;
        //this.interventionId = interventionId;
        //this.transmitted = transmitted;
        this.rappInterv = rappInterv;
    }

    public String getIntervenantNom() {
        return intervenantNom;
    }

    public void setIntervenantNom(String intervenantNom) {
        this.intervenantNom = intervenantNom;
    }

    public PanneEquipmentData() {
    }

    public String getPrevDate() {
        return prevDate;
    }

    public void setPrevDate(String prevDate) {
        this.prevDate = prevDate;
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

    public String getLieuAff() {
        return lieuAff;
    }

    public void setLieuAff(String lieuAff) {
        this.lieuAff = lieuAff;
    }

    public int getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(int interventionId) {
        this.interventionId = interventionId;
    }

    public Boolean getTransmitted() {
        return transmitted;
    }

    public void setTransmitted(Boolean transmitted) {
        this.transmitted = transmitted;
    }

    public boolean isTransmitted() {
        return transmitted;
    }

    public String getRappInterv() {
        return this.rappInterv;
    }

    public void setRappInterv(String rappInterv) {
        this.rappInterv = rappInterv;
    }
}

