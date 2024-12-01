package com.example.projetmaintbionew;

import java.sql.Timestamp;

public class Panne {

    private int panneId;
    private int equipmentId;
    private String description;
    private int idUser;

    public Panne() {
    }

    public Panne(int panneId, int equipmentId, String description, int idUser) {
        this.panneId = panneId;
        this.equipmentId = equipmentId;
        this.description = description;
        this.idUser = idUser;
    }

    public Panne(int equipmentId, String description, int idUser) {
        this.equipmentId = equipmentId;
        this.description = description;
        this.idUser = idUser;
    }

    public int getPanneId() {
        return panneId;
    }

    public void setPanneId(int panneId) {
        this.panneId = panneId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
