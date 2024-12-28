package com.example.projetmaintbionew;

import java.sql.Date;
import java.sql.Timestamp;

public class Equipement {

    private int userId;
    private int equipmentId;
    private Date anneAcquis;
    private int anneMisServ;
    private String designation;
    private String marque;
    private String model;
    private String numserie;
    private String sourceAcquis;
    private String etat;
    private String service;
    private String salleAff;


    private String panne;

    public Equipement() {
    }

    public String getPanne() {
        return panne;
    }

    public void setPanne(String panne) {
        this.panne = panne;
    }

    public Equipement(int userId, int equipmentId, Date anneAcquis, int anneMisServ, String designation, String marque, String model, String numserie, String sourceAcquis, String etat, String service, String salleAff) {
        this.userId = userId;
        this.equipmentId = equipmentId;
        this.anneAcquis = anneAcquis;
        this.anneMisServ = anneMisServ;
        this.designation = designation;
        this.marque = marque;
        this.model = model;
        this.numserie = numserie;
        this.sourceAcquis = sourceAcquis;
        this.etat = etat;
        this.service = service;
        this.salleAff = salleAff;
    }



    public Equipement(String marque, String numserie, String panne) {
        this.marque = marque;
        this.numserie = numserie;
        this.panne = panne;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Date getAnneAcquis() {
        return anneAcquis;
    }

    public void setAnneAcquis(Date anneAcquis) {
        this.anneAcquis = anneAcquis;
    }

    public Integer getAnneMisServ() {
        return anneMisServ;
    }

    public void setAnneMisServ(int anneMisServ) {
        this.anneMisServ = anneMisServ;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }

    public String getSourceAcquis() {
        return sourceAcquis;
    }

    public void setSourceAcquis(String sourceAcquis) {
        this.sourceAcquis = sourceAcquis;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSalleAff() {
        return salleAff;
    }

    public void setSalleAff(String salleAff) {
        this.salleAff = salleAff;
    }
}
