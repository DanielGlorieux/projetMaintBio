package com.example.projetmaintbionew;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import java.util.ResourceBundle;

public class GestionUtilisateurController implements Initializable {

    @FXML
    private JFXButton addUsrBtn;

    @FXML
    private JFXButton btnCreatUsr;

    @FXML
    private HBox change;

    @FXML
    private VBox changeP;

    @FXML
    private JFXButton deleteUsrBtn;

    @FXML
    private MenuItem miProfAdm;

    @FXML
    private MenuItem miProfTC;

    @FXML
    private MenuItem miProfTI;

    @FXML
    private MenuItem miProfUtili;

    @FXML
    private JFXTextField nameUsr;

    @FXML
    private MenuButton profileUsr;

    @FXML
    private JFXPasswordField pwdUsr;

    @FXML
    private JFXButton updUsrBtn;
    private String selectedProfile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        miProfUtili.setOnAction(event -> setSelectedProfile("Utilisateur"));
        miProfTI.setOnAction(event -> setSelectedProfile("Technicien_Intervenant"));
        miProfTC.setOnAction(event -> setSelectedProfile("Technicien_Chef"));
        miProfAdm.setOnAction(event -> setSelectedProfile("Administrateur"));
        addUsrBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    changeP.getChildren().clear();
                    changeP.getChildren().add((Node) FXMLLoader.load(getClass().getResource("ajouterUtilisateur.fxml")));
                    //changePane.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnCreatUsr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBHandler databaseHandler = new DBHandler();

                String profile = getSelectedProfile();


                if(!nameUsr.getText().equals("")&& !profile.equals("") && ! pwdUsr.getText().equals("")){

                    String userName = nameUsr.getText();
                    String password = pwdUsr.getText();


                    Utilisateur user = new Utilisateur(userName,password,profile);


                    DBHandler.addUser(user);

                    // Remise des champs à vides
                    nameUsr.setText("");
                    pwdUsr.setText("");

                    //profileUsr.setText("");

                }
            }
        });

        updUsrBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    changeP.getChildren().clear();
                    changeP.getChildren().add((Node) FXMLLoader.load(getClass().getResource("modifierUtilisateurView.fxml")));
                    //changePane.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        deleteUsrBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    changeP.getChildren().clear();
                    changeP.getChildren().add((Node) FXMLLoader.load(getClass().getResource("suppUtilisateurView.fxml")));
                    //changePane.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void setSelectedProfile(String profile) {
        this.selectedProfile = profile;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedProfile() {
        return selectedProfile;
    }
}
