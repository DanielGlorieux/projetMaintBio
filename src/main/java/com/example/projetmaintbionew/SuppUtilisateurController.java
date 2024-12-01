package com.example.projetmaintbionew;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SuppUtilisateurController implements Initializable {
    @FXML
    private JFXButton btnSupUsr;

    @FXML
    private JFXTextField nameUsr;

    private DBHandler databaseHandler;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        btnSupUsr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String nameText = nameUsr.getText().trim();
                databaseHandler = new DBHandler();

                try {
                    databaseHandler.deleteUser(nameText);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }


                nameUsr.setText("");
            }
        });
    }
}
