package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignalePanneV1Controller implements Initializable {

    private int userId;

    @FXML
    private JFXButton btnSignaler;

    @FXML
    private HBox change;

    @FXML
    private VBox changeP;

    @FXML
    private JFXTextArea descPanneTf;

    @FXML
    private JFXButton gestPanneBrnV1;

    @FXML
    private JFXTextField marqueAppTf;

    @FXML
    private JFXTextField numSerieTf;

    @FXML
    private JFXButton signPanneBtnV1;

    @FXML
    private JFXTextField designationAppTf;

    @FXML
    private MenuButton typePanne;

    @FXML
    private MenuItem miMaintPred;

    @FXML
    private MenuItem miPanneOcr;

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

        gestPanneBrnV1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    changeP.getChildren().clear();
                    changeP.getChildren().add((Node) FXMLLoader.load(getClass().getResource("gestPanneView.fxml")));
                    //changePane.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        signPanneBtnV1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    changeP.getChildren().clear();
                    changeP.getChildren().add((Node) FXMLLoader.load(getClass().getResource("signalPanneView12.fxml")));
                    //changePane.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    public int getUserId() {
        //System.out.println("from getUserId() " + userId);

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        //System.out.println("User ID set in CellEquipementController: " + this.userId);
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
