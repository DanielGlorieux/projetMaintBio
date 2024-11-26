package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class SignalPaneController {

    public int userId;

    public int equipmentId;

    @FXML
    private JFXButton btnSignal1;

    @FXML
    private JFXTextArea desripPanneTa;

    private DBHandler databaseHandler;




    public void setDesripPanneTa(String Signalement) {
        this.desripPanneTa.setText(Signalement);
    }

    public String getDesripPanneTa() {
        return this.desripPanneTa.getText().trim();
    }

    public JFXButton getBtnSignal() {
        return btnSignal1;
    }

    public int getUserId() {
        //System.out.println("from getUserId() " + userId);

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        //System.out.println("User ID set in SignalPaneController: " + this.userId);
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
        //System.out.println("Equipment ID in SignalPaneController: " + equipmentId);
    }

    public int getEquipmentId() {
        //System.out.println("from getUserId() " + userId);

        return equipmentId;
    }

    @FXML
    void initialize() {

        databaseHandler = new DBHandler();

        btnSignal1.setOnAction(event -> {
            String descText = desripPanneTa.getText().trim();
            System.out.println("Description: " + descText);
            System.out.println("User ID: " + userId);
            System.out.println("Equipment ID: " + equipmentId);

            if (!descText.isEmpty() && userId != 0 && equipmentId != 0) {
                Panne pa = new Panne();
                pa.setDescription(descText);
                pa.setIdUser(userId);
                pa.setEquipmentId(equipmentId);

                databaseHandler.addPane(pa);
                System.out.println("Panne has been sent to DBHandler!");
            } else {
                System.out.println("Validation failed! Ensure all fields are filled.");
            }
        });



    }
}
