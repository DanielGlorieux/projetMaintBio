package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignalPanne12Controller implements Initializable {
    @FXML
    private JFXButton btnSignaler;

    @FXML
    private VBox changeP;

    @FXML
    private JFXTextArea descPanneTf;

    @FXML
    private JFXTextField designationAppTf;

    @FXML
    private JFXTextField marqueAppTf;

    @FXML
    private MenuItem miMaintPred;

    @FXML
    private MenuItem miPanneOcr;

    @FXML
    private JFXTextField numSerieTf;

    @FXML
    private MenuButton typePanne;
    private String selectedType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        miMaintPred.setOnAction(event -> setSelectedType("Maintenance prédictive"));
        miPanneOcr.setOnAction(event -> setSelectedType("Panne occurente"));


        DBHandler dbHandler = new DBHandler();

        btnSignaler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String numSerieText = numSerieTf.getText().trim();
                String marqueAppText = marqueAppTf.getText().trim();
                String descPanneText = descPanneTf.getText().trim();
                String typeText = selectedType;


                // Enregistrement dans la base de données
                try {
                    dbHandler.addPanne(descPanneText,numSerieText,marqueAppText, typeText);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                // Remise des champs à vides
                numSerieTf.setText("");
                marqueAppTf.setText("");
                descPanneTf.setText("");
            }
        });
    }

    private void setSelectedType(String profile) {
        this.selectedType = profile;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedType() {
        return selectedType;
    }
}
