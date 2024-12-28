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
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainPageTCController implements Initializable {

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
    private JFXButton equipGesBtn;

    @FXML
    private JFXButton equipGesBtn1;

    @FXML
    private JFXButton exitBtn;

    @FXML
    private JFXButton intervGesBtn;

    @FXML
    private ImageView logoBtn1;

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

    @FXML
    private JFXButton usrGesBtn;

    @FXML
    private JFXButton signaPanneBtn;

    @FXML
    private JFXPasswordField pwdUsr1;

    @FXML
    private JFXButton btnCreatUsr1;

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

        Image icon7 = new Image(getClass().getResourceAsStream("/interv.png"));
        ImageView iconView7 = new ImageView(icon7);
        iconView7.setFitWidth(15);
        iconView7.setFitHeight(15);
        intervGesBtn.setGraphic(iconView7);
        intervGesBtn.setMinWidth(197);
        intervGesBtn.setMinHeight(34);


        Image icon8 = new Image(getClass().getResourceAsStream("/statistiques.png"));
        ImageView iconView8 = new ImageView(icon8);
        iconView8.setFitWidth(15);
        iconView8.setFitHeight(15);
        equipGesBtn1.setGraphic(iconView8);
        equipGesBtn1.setMinWidth(197);
        equipGesBtn1.setMinHeight(34);


        final String[] profileTexte = new String[1];


        btnCreatUsr1.setOnAction(new EventHandler<ActionEvent>() {
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
                    Node subView = loader.load();

                    GestionUtilisateurUserController subController = loader.getController();
                    subController.setUsername(username);
                    subController.setProfile(profile);

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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionEquipementView.fxml"));
                    Node gestionEquipementView = loader.load();
                    GestionEquipementController gestionEquipementController = loader.getController();
                    //gestionEquipementController.setUserId(userId);

                    change.getChildren().clear();
                    change.getChildren().add(gestionEquipementView);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        equipGesBtn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("statisticsView.fxml"));
                    Node statisticsView = loader.load();
                    StatisticsController statisticsController = loader.getController();
                    //statisticsController.setUserId(userId);

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
                    //signalPController.setUserId(userId);

                    change.getChildren().clear();
                    change.getChildren().add(signalPView);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        intervGesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    change.getChildren().clear();
                    change.getChildren().add((Node)FXMLLoader.load(getClass().getResource("listPanneView.fxml")) );
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


}
