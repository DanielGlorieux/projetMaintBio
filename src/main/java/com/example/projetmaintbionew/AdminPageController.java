package com.example.projetmaintbionew;


import animations.Shaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AdminPageController implements Initializable {

    private int userId;

    @FXML
    private JFXTextField admN;

    @FXML
    private JFXPasswordField admPwd;

    @FXML
    private JFXButton btnCoAdm;


    @FXML
    private JFXButton logoBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image icon2 = new Image(getClass().getResourceAsStream("/logoMaintBio1.png"));
        ImageView iconView2 = new ImageView(icon2);
        iconView2.setFitWidth(65);
        iconView2.setFitHeight(65);
        logoBtn.setGraphic(iconView2);
        logoBtn.setMinWidth(66);
        logoBtn.setMinHeight(66);


        btnCoAdm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String loginText = admN.getText().trim();
                String loginPwd = admPwd.getText().trim();

                Utilisateur user = new Utilisateur();
                user.setNom(loginText);
                user.setCode(loginPwd);

                ResultSet userRow = DBHandler.getUser(user);

                String profile1 = DBHandler.getUserProfile(loginText,loginPwd);

                int counter = 0;

                try {
                    while (userRow.next()) {
                        counter++;
                        String name = userRow.getString("name");
                        int userIdFromDB = userRow.getInt("iduser");
                       // System.out.println("Welcome! " + name);
                        AppSession.getInstance().setUserId(userIdFromDB);

                    }

                    if (counter == 1) {
                        if (profile1.equals("Administrateur")){
                            showAddItemScreen();
                        }
                    }else {
                        Shaker userNameShaker = new Shaker(admN);
                        Shaker passwordShaker = new Shaker(admPwd);
                        passwordShaker.shake();
                        userNameShaker.shake();

                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    private void showAddItemScreen() {

        btnCoAdm.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("mainPageView.fxml"));

        try {
            loader.setRoot(loader.getRoot());
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.showAndWait();
    }
}
