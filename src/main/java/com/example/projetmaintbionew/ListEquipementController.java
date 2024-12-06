package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXListView;
import databaseHelper.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListEquipementController implements Initializable {

    private static int userId;

    @FXML
    private JFXListView<Equipement> listEquip;
    private ObservableList<Equipement> equipmts;
    //private ObservableList<Task> refreshedTasks;

    private DBHandler databaseHandler;


    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        System.out.println("initialize called");

        equipmts = FXCollections.observableArrayList();


        databaseHandler = new DBHandler();
        ResultSet resultSet = databaseHandler.getEquipment();

        while (resultSet.next()) {
            Equipement equipmt = new Equipement();
            equipmt.setNumserie(resultSet.getString("num_serie"));
            equipmt.setDesignation(resultSet.getString("designation"));
            equipmt.setEtat(resultSet.getString("etat"));
            equipmt.setService(resultSet.getString("service"));
            equipmt.setAnneAcquis(resultSet.getTimestamp("annee_acquis"));

            equipmts.addAll(equipmt);

        }

        listEquip.setItems(equipmts);
        listEquip.setCellFactory(cellEquipementController -> new cellEquipementController());

    }*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //System.out.println("initialize called");

        equipmts = FXCollections.observableArrayList();
        databaseHandler = new DBHandler();

        try {
            loadEquipments();
        } catch (SQLException e) {
            e.printStackTrace(); // Or handle it appropriately
        }

        listEquip.setItems(equipmts);

        listEquip.setCellFactory(cellEquipement -> {
            cellEquipementController cellController = new cellEquipementController();
            cellController.setUserId(userId); // Pass userId to the cell controller
            return cellController;
        });
    }

    private void loadEquipments() throws SQLException {
        ResultSet resultSet = databaseHandler.getEquipment();

        while (resultSet.next()) {
            Equipement equipmt = new Equipement();
            equipmt.setNumserie(resultSet.getString("num_serie"));
            equipmt.setDesignation(resultSet.getString("designation"));
            equipmt.setEtat(resultSet.getString("etat"));
            equipmt.setService(resultSet.getString("service"));
            equipmt.setAnneAcquis(resultSet.getTimestamp("annee_acquis"));
            equipmt.setEquipmentId(resultSet.getInt("idequipment"));

            equipmts.add(equipmt); // Corrected to add instead of addAll
        }
    }

    public int getUserId() {
        //System.out.println("from getUserId() " + userId);

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        //System.out.println("User ID set in ListEquipementController: " + this.userId);
    }

}
