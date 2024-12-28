package com.example.projetmaintbionew;

import databaseHelper.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestPanneController implements Initializable {


    @FXML
    private VBox changeP;

    @FXML
    private TableView<PanneEquipmentData> tbPanne;

    @FXML
    private TableColumn<PanneEquipmentData, String> tcDescript;

    @FXML
    private TableColumn<PanneEquipmentData, String> tcDesignation;

    @FXML
    private TableColumn<PanneEquipmentData, String> tcMarque;

    @FXML
    private TableColumn<PanneEquipmentData, String> tcStatut;

    @FXML
    private TableColumn<PanneEquipmentData, String> tcRappInt;

    @FXML
    private TableColumn<PanneEquipmentData, String> tcTypeP;

    @FXML
    private TableColumn<PanneEquipmentData, String> tcIntervenant;

    private ObservableList<PanneEquipmentData> donnes = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DBHandler dbHandler = new DBHandler();

        tcDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        tcMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        tcDescript.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcTypeP.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tcRappInt.setCellValueFactory(new PropertyValueFactory<>("rappInterv"));
        tcIntervenant.setCellValueFactory(new PropertyValueFactory<>("intervenantNom"));

        try {
            ResultSet res = dbHandler.getPanneAndEquipmentData();
            while (res.next()) {
                String description = res.getString("description");
                String marque = res.getString("marque");
                String designation = res.getString("designation");
                String type = res.getString("type");
                String statut = res.getString("statut");
                String rappIntervention = res.getString("rappInterv");
                String intervenant = res.getString("name");


                PanneEquipmentData panne = new PanneEquipmentData();
                panne.setDesignation(designation);
                panne.setMarque(marque);
                panne.setDescription(description);
                panne.setStatut(statut);
                panne.setType(type);
                panne.setRappInterv(rappIntervention);
                panne.setIntervenantNom(intervenant);

                //PanneEquipmentData p = new PanneEquipmentData(designation, marque,description, statut, type, rappIntervention)
                // Add data to the observable list
                donnes.add(panne);
                tbPanne.setItems(donnes);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}