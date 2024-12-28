package com.example.projetmaintbionew;


import animations.Fader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class AjouterUtilisateurController implements Initializable {


    @FXML
    private JFXButton btnCreatUsr;

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

    private String selectedProf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBHandler dbHandler = new DBHandler();

        miProfUtili.setOnAction(event -> setSelectedProf("Utilisateur"));
        miProfTI.setOnAction(event -> setSelectedProf("Technicien_Intervenant"));
        miProfTC.setOnAction(event -> setSelectedProf("Technicien_Chef"));
        miProfAdm.setOnAction(event -> setSelectedProf("Administrateur"));

        btnCreatUsr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String profile = getSelectedProf();


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
    }

    public void setSelectedProf(String proffA) {
        this.selectedProf = proffA;
        //System.out.println("Selected Profile: " + selectedProfile);
        // You can perform additional actions here based on the selected profile
    }

    public String getSelectedProf() {
        return selectedProf;
    }
}
