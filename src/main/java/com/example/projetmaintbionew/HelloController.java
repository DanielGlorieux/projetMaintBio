package com.example.projetmaintbionew;

import animations.Shaker;
import databaseHelper.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private int userId;

    @FXML
    private JFXButton btnAdm;

    @FXML
    private JFXButton btnCo;

    @FXML
    private JFXTextField usrN;

    @FXML
    private JFXPasswordField usrPwd;

    @FXML
    private ImageView logoImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image icon1 = new Image(getClass().getResourceAsStream("/logoMaintBio1.png"));
        ImageView iconView1 = new ImageView(icon1);
        iconView1.setFitWidth(65);
        iconView1.setFitHeight(65);
        /*logoBtn.setGraphic(iconView1);
        logoBtn.setMinWidth(66);
        logoBtn.setMinHeight(66);*/

        btnAdm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnCo.getScene().getWindow().hide();
                Stage stage = new Stage();
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("adminPageView.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("MaintBio");

                    //Image ic = new Image(getClass().getResource("/logo.png").toExternalForm());
                    //stage.getIcons().add(ic);
                    stage.setScene(scene);
                }catch (IOException e){
                    System.out.println(e);
                }
                Image ic = new Image(getClass().getResource("/logoMaintBio1.png").toExternalForm());
                stage.getIcons().add(ic);
                stage.show();
            }
        });

        btnCo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String loginText = usrN.getText().trim();
                String loginPwd = usrPwd.getText().trim();

                Utilisateur user = new Utilisateur();
                user.setNom(loginText);
                user.setCode(loginPwd);

                String profile1 = DBHandler.getUserProfile(loginText,loginPwd);

                //System.out.println("Profile :" + profile1);

                ResultSet userRow = DBHandler.getUser(user);

                int counter = 0;

                /*try {
                    if (userRow != null && userRow.next()) {
                        String name = userRow.getString("name");
                        String profile = userRow.getString("profile");
                        userId = userRow.getInt("iduser");


                        // Pass data to the main page
                        showMainPage(name, profile);
                    } else {
                        // Shake inputs on invalid login
                        Shaker userNameShaker = new Shaker(usrN);
                        Shaker passwordShaker = new Shaker(usrPwd);
                        userNameShaker.shake();
                        passwordShaker.shake();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }*/

                try {
                    while (userRow.next()) {
                        counter++;
                        int userIdFromDB = userRow.getInt("iduser");
                        //System.out.println("Retrieved userId: " + userIdFromDB);
                        AppSession.getInstance().setUserId(userIdFromDB);

                    }

                    if (counter == 1) {

                        if(profile1.equals("Utilisateur")){
                            showMainPage(loginText,profile1);
                        }
                        if (profile1.equals("Technicien_Chef")){
                            showMainPageTC(loginText,profile1);
                        }

                        if (profile1.equals("Technicien_Intervenant")){
                            showMainPageTI(loginText,profile1);
                        }

                        //showAddItemScreen();

                    }else {
                        Shaker userNameShaker = new Shaker(usrN);
                        Shaker passwordShaker = new Shaker(usrPwd);
                        passwordShaker.shake();
                        userNameShaker.shake();

                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                /*try {
                    while (userRow.next()) {
                        counter++;
                        String name = userRow.getString("name");
                        userId = userRow.getInt("iduser");
                        System.out.println("Welcome! " + name);

                    }

                    if (counter == 1) {
                        showAddItemScreen();
                    }else {
                        Shaker userNameShaker = new Shaker(usrN);
                        Shaker passwordShaker = new Shaker(usrPwd);
                        passwordShaker.shake();
                        userNameShaker.shake();

                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }*/

            }
        });


    }

    private void showAddItemScreen() {

        btnCo.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("mainPageUserView.fxml"));

        try {
            loader.setRoot(loader.getRoot());
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        /*MainPageController mainPageController = loader.getController();
        mainPageController.setUserId(userId);*/

        Image ic = new Image(getClass().getResource("/logoMaintBio1.png").toExternalForm());
        stage.getIcons().add(ic);

        stage.showAndWait();
    }

    private void showMainPage(String name, String profile) {
        btnCo.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPageUserView.fxml"));
            Parent root = loader.load();

            //Node mainPageUserView = loader.load();


            // Get controller and set data
            MainPageUserController mainPageUserController = loader.getController();
            mainPageUserController.setUsername(name);
            mainPageUserController.setProfile(profile);
            //controller.setProfile(profile);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            Image ic = new Image(getClass().getResource("/logoMaintBio1.png").toExternalForm());
            stage.getIcons().add(ic);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMainPageTC(String name, String profile) {
        btnCo.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPageTCView.fxml"));
            Parent root = loader.load();

            //Node mainPageUserView = loader.load();


            // Get controller and set data
            MainPageTCController mainPageTCController = loader.getController();
            mainPageTCController.setUsername(name);
            mainPageTCController.setProfile(profile);
            //mainPageTCController.setUserId(userId);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            Image ic = new Image(getClass().getResource("/logoMaintBio1.png").toExternalForm());
            stage.getIcons().add(ic);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMainPageTI(String name, String profile) {
        btnCo.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPageTIView.fxml"));
            Parent root = loader.load();

            //Node mainPageUserView = loader.load();


            // Get controller and set data
            MainPageTIController mainPageTIController = loader.getController();
            mainPageTIController.setUsername(name);
            mainPageTIController.setProfile(profile);
            //mainPageTCController.setUserId(userId);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            Image ic = new Image(getClass().getResource("/logoMaintBio1.png").toExternalForm());
            stage.getIcons().add(ic);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}