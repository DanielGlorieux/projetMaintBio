package com.example.projetmaintbionew;
import com.jfoenix.controls.JFXComboBox;
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
import java.util.stream.Collectors;

public class ListEquipementUserController implements Initializable {

    @FXML
    private JFXListView<Equipement> listEquip;
    private ObservableList<Equipement> equipmts;
    //private ObservableList<Task> refreshedTasks;

    @FXML
    private JFXComboBox<String> filterServiceComboBox;

    private DBHandler databaseHandler;



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
            CellEquipementUserController cellController = new CellEquipementUserController();
            //cellController.setUserId(userId); // Pass userId to the cell controller
            return cellController;
        });

        ObservableList<String> services = FXCollections.observableArrayList(
                equipmts.stream()
                        .map(Equipement::getService) // Assuming Equipement has a getService() method
                        .distinct()
                        .collect(Collectors.toList())
        );
        services.add(0, "Tous les services"); // Add an option for showing all services
        filterServiceComboBox.setItems(services);

        filterServiceComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            filterEquipmentByService(newValue);
        });
    }

    private void filterEquipmentByService(String service) {
        if (service == null || service.equals("Tous les services")) {
            listEquip.setItems(equipmts); // Show all equipment
        } else {
            ObservableList<Equipement> filteredList = FXCollections.observableArrayList(
                    equipmts.stream()
                            .filter(equipment -> service.equals(equipment.getService()))
                            .collect(Collectors.toList())
            );
            listEquip.setItems(filteredList);
        }
    }

    private void loadEquipments() throws SQLException {
        equipmts.clear();
        ResultSet resultSet = databaseHandler.getEquipment();

        while (resultSet.next()) {
            Equipement equipmt = new Equipement();
            equipmt.setNumserie(resultSet.getString("num_serie"));
            equipmt.setDesignation(resultSet.getString("designation"));
            equipmt.setEtat(resultSet.getString("etat"));
            equipmt.setService(resultSet.getString("service"));
            equipmt.setAnneAcquis(resultSet.getDate("annee_acquis"));
            equipmt.setEquipmentId(resultSet.getInt("idequipment"));

            equipmts.add(equipmt); // Corrected to add instead of addAll
        }
    }
}
