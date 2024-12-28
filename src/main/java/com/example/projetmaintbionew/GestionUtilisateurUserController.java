package com.example.projetmaintbionew;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestionUtilisateurUserController implements Initializable {

    @FXML
    private JFXButton btnCreatUsr;

    @FXML
    private HBox change;

    @FXML
    private VBox changeP;

    @FXML
    private JFXPasswordField pwdUsr;

    public String username;
    public String profile;

    // Getters and setters for username and profile
    public void setUsername(String username) {
        this.username = username;
        System.out.println("Username set: " + username); // Debugging log
    }

    public void setProfile(String profile) {
        this.profile = profile;
        System.out.println("Profile set: " + profile); // Debugging log
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller initialized. Username: " + username + ", Profile: " + profile);

        btnCreatUsr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                if (!pwdUsr.getText().equals("")){
                    String pass = pwdUsr.getText().trim();
                    try {
                        DBHandler.updateCredit(pass,username,profile);
                        pwdUsr.setText("");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        });
    }
}
