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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainPageUserController implements Initializable {


    @FXML
    private JFXButton btnCreatUsr;

    @FXML
    private HBox change;

    @FXML
    private VBox changeP;

    @FXML
    private JFXButton equipGesBtn;

    @FXML
    private JFXButton equipGesBtn1;

    @FXML
    private JFXButton exitBtn;

    @FXML
    private ImageView logoBtn1;

    @FXML
    private JFXPasswordField pwdUsr1;

    @FXML
    private JFXButton signaPanneBtn;

    @FXML
    private JFXButton usrGesBtn;


    private String selectedProfile;
    public static int userId;

    private String username;
    private String profile;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //System.out.println("Profile : " + profile);


        Image icon2 = new Image(getClass().getResourceAsStream("/logoMaintBio1.png"));
        ImageView iconView2 = new ImageView(icon2);
        iconView2.setFitWidth(65);
        iconView2.setFitHeight(65);

        Image icon1 = new Image(getClass().getResourceAsStream("/logOut.png"));
        ImageView iconView1 = new ImageView(icon1);
        iconView1.setFitWidth(20);
        iconView1.setFitHeight(20);
        exitBtn.setGraphic(iconView1);
        exitBtn.setMinWidth(125);
        exitBtn.setMinHeight(34);

        Image icon3 = new Image(getClass().getResourceAsStream("/users.png"));
        ImageView iconView3 = new ImageView(icon3);
        iconView3.setFitWidth(15);
        iconView3.setFitHeight(15);
        usrGesBtn.setGraphic(iconView3);
        usrGesBtn.setMinWidth(197);
        usrGesBtn.setMinHeight(34);

        Image icon4 = new Image(getClass().getResourceAsStream("/equipement.png"));
        ImageView iconView4 = new ImageView(icon4);
        iconView4.setFitWidth(15);
        iconView4.setFitHeight(15);
        equipGesBtn.setGraphic(iconView4);
        equipGesBtn.setMinWidth(197);
        equipGesBtn.setMinHeight(34);

        Image icon5 = new Image(getClass().getResourceAsStream("/signals.png"));
        ImageView iconView5 = new ImageView(icon5);
        iconView5.setFitWidth(15);
        iconView5.setFitHeight(15);

        Image icon6 = new Image(getClass().getResourceAsStream("/alerte.png"));
        ImageView iconView6 = new ImageView(icon6);
        iconView6.setFitWidth(15);
        iconView6.setFitHeight(15);
        signaPanneBtn.setGraphic(iconView6);
        signaPanneBtn.setMinWidth(197);
        signaPanneBtn.setMinHeight(34);



        Image icon8 = new Image(getClass().getResourceAsStream("/statistiques.png"));
        ImageView iconView8 = new ImageView(icon8);
        iconView8.setFitWidth(15);
        iconView8.setFitHeight(15);
        equipGesBtn1.setGraphic(iconView8);
        equipGesBtn1.setMinWidth(197);
        equipGesBtn1.setMinHeight(34);

        btnCreatUsr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (!pwdUsr1.getText().equals("")){
                    String pass = pwdUsr1.getText().trim();
                    try {
                        DBHandler.updateCredit(pass,username,profile);
                        pwdUsr1.setText("");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        });



        usrGesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionUtilisateurUserView.fxml"));
                    Node subView = loader.load(); // Always load first before accessing the controller

                    // Retrieve the controller and pass the data
                    GestionUtilisateurUserController subController = loader.getController();
                    subController.setUsername(username);
                    subController.setProfile(profile);

                    // Add the subview to the HBox
                    change.getChildren().clear();
                    change.getChildren().add(subView);
                    //changePane.setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        equipGesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("listEquipementUserView.fxml"));
                    Node listEquipementView = loader.load();

                    // Pass user ID to ListEquipementController
                    ListEquipementUserController listEquipementController = loader.getController();
                    //listEquipementController.setUserId(userId); // Use the userId from GestionEquipementController

                    change.getChildren().clear();
                    change.getChildren().add(listEquipementView);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                /*try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionEquipementView.fxml"));
                    Node gestionEquipementView = loader.load();
                    GestionEquipementController gestionEquipementController = loader.getController();
                    gestionEquipementController.setUserId(userId);

                    change.getChildren().clear();
                    change.getChildren().add(gestionEquipementView);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }*/
            }
        });

        equipGesBtn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("statisticsView.fxml"));
                    Node statisticsView = loader.load();
                    StatisticsController statisticsController = loader.getController();
                    statisticsController.setUserId(userId);

                    change.getChildren().clear();
                    change.getChildren().add(statisticsView);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        signaPanneBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("signalPanneView1.fxml"));
                    Node signalPView = loader.load();
                    SignalePanneV1Controller signalPController = loader.getController();
                    signalPController.setUserId(userId);

                    change.getChildren().clear();
                    change.getChildren().add(signalPView);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exitBtn.getScene().getWindow().hide();
                Stage stage = new Stage();
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
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
    }

    public void setUserId(int userId) {

        this.userId = userId;
        //System.out.println("User Id is " + this.userId);

    }

    public int getUserId(){
        return this.userId;
    }

    /*public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
        //System.out.println("From login page " + this.profile);
    }*/
}
