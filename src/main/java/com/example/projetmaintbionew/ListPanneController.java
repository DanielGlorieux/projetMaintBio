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

public class ListPanneController implements Initializable {

    @FXML
    private JFXListView<PanneEquipmentData> listPanne;

    private ObservableList<PanneEquipmentData> interventions;
    //private ObservableList<Task> refreshedTasks;

    private DBHandler databaseHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        interventions = FXCollections.observableArrayList();
        databaseHandler = new DBHandler();

        try {
            loadInterventions();
        } catch (SQLException e) {
            e.printStackTrace(); // Or handle it appropriately
        }

        listPanne.setItems(interventions);

        listPanne.setCellFactory(cellIntervention -> {
            CellPanneController cellController = new CellPanneController();
            //cellController.setUserId(userId); // Pass userId to the cell controller
            return cellController;
        });
    }

    private void loadInterventions() throws SQLException {
        ResultSet resultSet = databaseHandler.getEquipmentWithPanneNonTraite2();

        while (resultSet.next()) {
            PanneEquipmentData interv = new PanneEquipmentData();
            interv.setMarque(resultSet.getString("marque"));
            interv.setDesignation(resultSet.getString("designation"));
            interv.setLieuAff(resultSet.getString("service"));
            interv.setDescription(resultSet.getString("description"));
            interv.setInterventionId(resultSet.getInt("idintervention")); // Set the intervention ID
            interv.setTransmitted(resultSet.getBoolean("transmitted"));
            interv.setPrevDate(resultSet.getString("datePlanif"));
            interventions.add(interv);
        }
    }

}
