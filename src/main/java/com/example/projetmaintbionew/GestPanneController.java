package com.example.projetmaintbionew;

import databaseHelper.DBHandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
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
    private TableColumn<PanneEquipmentData, String> tcTypeP;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DBHandler dbHandler = new DBHandler();

        tcDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        tcMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        tcDescript.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcTypeP.setCellValueFactory(new PropertyValueFactory<>("type"));
        tcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        ObservableList<PanneEquipmentData> data = dbHandler.getPanneAndEquipmentData();
        tbPanne.setItems(data);

    }
}