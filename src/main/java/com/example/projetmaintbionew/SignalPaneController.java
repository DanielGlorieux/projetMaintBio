package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignalPaneController implements Initializable {
    @FXML
    private JFXButton btnSignal1;

    @FXML
    private JFXTextArea desripPanneTa;



    public int userId;

    public int equipmentId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

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
}
